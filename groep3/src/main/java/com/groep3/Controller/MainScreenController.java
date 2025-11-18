package com.groep3.Controller;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import com.groep3.Model.Fruit;

//Deze class is om de content van het mainscreen te controleren

public class MainScreenController {

    @FXML
    private GridPane fruitItems;

    private FruitController fruitController;

    public void setFruitController(FruitController fruitController) {
        this.fruitController = fruitController;
        loadFruits();
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
                "-fx-background-color: red; " +
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
        noImg.setStyle("-fx-text-fill: #888; -fx-font-size: 14px; -fx-background-color: green");
        imgBox.getChildren().add(noImg);

        // Fruitnaam
        Label name = new Label(fruit.getName());
        name.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-background-color: yellow");

        // Beschrijving
        Label description = new Label(fruit.getDescription());
        description.setStyle("-fx-text-fill: #666666; -fx-font-size: 14px;-fx-background-color: blue");
        description.setWrapText(true);
        description.setMaxWidth(200);

        // Prijs
        Label price = new Label("€ " + String.format("%.2f", fruit.getPrice()));
        price.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #27ae60;");

        infoContainer.getChildren().addAll( name, description, price);

        fruitContainer.getChildren().addAll(imgBox, infoContainer );
        return fruitContainer;
    }
}