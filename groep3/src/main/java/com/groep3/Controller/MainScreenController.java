package com.groep3.Controller;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.collections.FXCollections;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.io.IOException;

import com.groep3.App;
import com.groep3.Model.Fruit;

public class MainScreenController {

    @FXML private GridPane fruitItems;
    @FXML private TextField searchBar;
    @FXML private ListView<String> winkelmandList;
    @FXML private Label total;
    @FXML private Button afrekenButton;

    private FruitController fruitController;
    private ShoppingCartController shoppingCartController;

    public void setFruitController(FruitController fruitController, ShoppingCartController shoppingCartController) {
        this.fruitController = fruitController;
        this.shoppingCartController = shoppingCartController;
        loadFruits();
        initializeWinkelmand();
        init();
    }

    private void init() {
        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            fruitItems.getChildren().clear();

            int column = 0;
            int row = 0;

            for (Fruit fruit : fruitController.getFruits()) {
                if (fruit.getName().toLowerCase().contains(newValue.toLowerCase())) {
                    VBox card = fruitBox(fruit);
                    fruitItems.add(card, column, row);

                    column++;
                    if (column == 3) { column = 0; row++; }
                }
            }
        });
    }

    private void loadFruits() {
        int column = 0;
        int row = 0;

        for (Fruit fruit : fruitController.getFruits()) {
            VBox card = fruitBox(fruit);
            fruitItems.add(card, column, row);

            column++;
            if (column == 3) { column = 0; row++; }
        }
    }

    private VBox fruitBox(Fruit fruit) {
        VBox fruitContainer = new VBox(10);
        fruitContainer.setPrefSize(240, 360);
        fruitContainer.setStyle(
                "-fx-background-color: #eee; " +
                "-fx-border-color: #e0e0e0; " +
                "-fx-border-radius: 15; " +
                "-fx-background-radius: 15; " +
                "-fx-padding: 10; " +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 15, 0, 0, 5);"
        );

        fruitContainer.setOnMouseClicked(e -> openPopup(fruit));

        VBox imgBox = new VBox();

        if (fruit.getImagePath() != null) {
            imgBox.setAlignment(Pos.CENTER);
            imgBox.setPrefSize(140, 140);
            imgBox.setStyle("-fx-background-color: #ddd; -fx-background-radius: 10;");

            Image image = new Image(
                    App.class.getResourceAsStream(fruit.getImagePath()),
                    120, 120, true, true
            );

            ImageView iv = new ImageView(image);
            iv.fitWidthProperty().bind(imgBox.widthProperty());
            iv.fitHeightProperty().bind(imgBox.heightProperty());
            iv.setPreserveRatio(false);

            imgBox.getChildren().add(iv);

        } else {
            Label noImg = new Label("No Image");
            imgBox.getChildren().add(noImg);
        }

        Label name = new Label(fruit.getName());
        name.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Label description = new Label(fruit.getDescription());
        description.setStyle("-fx-text-fill: #666; -fx-font-size: 14px;");
        description.setWrapText(true);
        description.setMaxWidth(200);

        Label price = new Label("€ " + String.format("%.2f", fruit.getPrice()));
        price.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #27ae60;");

        Button addButton = new Button("+");
        addButton.setStyle(
                "-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: yellow;" +
                "-fx-pref-width: 44px; -fx-pref-height: 44px;" +
                "-fx-background-radius: 22; -fx-background-color: green;"
        );

        addButton.setOnAction(e -> {
            e.consume(); // zorgt ervoor dat de popup niet geopent kan worden als je op + klikt
            shoppingCartController.add(fruit);
            updateShoppingCart();
            total.setText("Total: € " + String.format("%.2f", shoppingCartController.getTotal()));
        });

        VBox infoContainer = new VBox(4);
        infoContainer.setAlignment(Pos.CENTER_LEFT);
        infoContainer.getChildren().addAll(name, description, price);

        fruitContainer.getChildren().addAll(imgBox, infoContainer, addButton);
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

    @FXML
    public void checkout() throws IOException {
        App.setRoot("order");
    }

    public void initializeWinkelmand() {
        total.setText("Total: € " + String.format("%.2f", shoppingCartController.getTotal()));
        winkelmandList.setItems(FXCollections.observableArrayList());
    }

    private void openPopup(Fruit fruit) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("productPopup.fxml"));
            Parent root = loader.load();

            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setScene(new Scene(root));
            popupStage.setResizable(false);

            ProductPopupController controller = loader.getController();
            controller.setStage(popupStage);
            controller.setFruit(fruit);
            controller.setShoppingCart(shoppingCartController);

            popupStage.showAndWait();

            updateShoppingCart();
            total.setText("Total: € " + String.format("%.2f", shoppingCartController.getTotal()));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
