package com.groep3.Controller;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.collections.FXCollections;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.groep3.Model.Fruit;

//Deze class is om de content van het mainscreen te controleren

public class MainScreenController {

    @FXML
    private GridPane fruitItems;

    @FXML
    private ListView<String> winkelmandList;

    @FXML
    private Label total;

    private FruitController fruitController;
    private ShoppingCartController shoppingCartController;
    public void setFruitController(FruitController fruitController, ShoppingCartController shoppingCartController) {
        this.fruitController = fruitController;
        this.shoppingCartController = shoppingCartController;
        loadFruits();
        initializeWinkelmand();
    }

    private void loadFruits() {

        int column = 0;
        int row = 0;
        final int MAX_COLUMNS = 3;

        for (Fruit fruit : fruitController.getFruits()) {
            VBox card = fruitBox(fruit);
            fruitItems.add(card, column, row);

            column++;
            if (column == MAX_COLUMNS) {
                column = 0;
                row++;
            }
        }
    }

    private VBox fruitBox(Fruit fruit) {
        VBox fruitContainer = new VBox(10);
        fruitContainer.setPrefSize(240, 360);
        fruitContainer.setStyle(
                "-fx-background-color: yellow; " +
                "-fx-border-color: #e0e0e0; " +
                "-fx-border-radius: 15; " +
                "-fx-background-radius: 15; " +
                "-fx-padding: 10; " +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 15, 0, 0, 5);"
        );

        VBox infoContainer = new VBox(4);
        infoContainer.setAlignment(Pos.CENTER_LEFT);
        // Wel een foto
        VBox imgBox = new VBox();
        imgBox.setAlignment(Pos.CENTER);
        imgBox.setPrefSize(140, 140);
        imgBox.setStyle("-fx-background-color: #ddd; -fx-background-radius: 10;");

        // Geen foto
        Label noImg = new Label("No Image");
        noImg.setStyle("-fx-text-fill: #888; -fx-font-size: 14px;");
        imgBox.getChildren().add(noImg);

        // Fruitnaam
        Label name = new Label(fruit.getName());
        name.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        // Beschrijving
        Label description = new Label(fruit.getDescription());
        description.setStyle("-fx-text-fill: #666666; -fx-font-size: 14px;");
        description.setWrapText(true);
        description.setMaxWidth(200);

        // Prijs
        Label price = new Label("€ " + String.format("%.2f", fruit.getPrice()));
        price.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #27ae60;");

        // Winkel wagen button
        Button addButton = new Button("+");
        addButton.setStyle(
            "-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: yellow; " +
            "-fx-pref-width: 44px; -fx-pref-height: 44px; " +
            "-fx-background-radius: 22; -fx-background-color: green;");

        addButton.setOnAction(
            e -> {
                shoppingCartController.add(fruit);
                updateShoppingCart();
                System.out.println(fruit.getName() + " added to cart.");
                total.setText("Total: € " + String.format("%.2f", shoppingCartController.getTotal()));
            }
        );

        infoContainer.getChildren().addAll( name, description, price);

        fruitContainer.getChildren().addAll(imgBox, infoContainer, addButton );
        return fruitContainer;
    }

    private void updateShoppingCart() {
        List<String> itemNames = new ArrayList<>();
        for (Map.Entry<Fruit, Integer> entry : shoppingCartController.getCartItems().entrySet()) {
            Fruit fruit = entry.getKey();
            int count = entry.getValue();
            double totalPrice = fruit.getPrice() * count;
            itemNames.add(fruit.getName() + " x" + count + " - €" + String.format("%.2f", totalPrice));
        }
        winkelmandList.setItems(FXCollections.observableArrayList(itemNames));
    }
    
    public void initializeWinkelmand() {
        total.setText("Total: € " + String.format("%.2f", shoppingCartController.getTotal()));
        winkelmandList.setItems(FXCollections.observableArrayList());
    }
}