package com.groep3.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import com.groep3.Model.Fruit;

//Deze class is om de content van het mainscreen te controleren

public class MainScreenController {

    @FXML
    private GridPane itemsGrid;

    private FruitController fruitController;

    public void setFruitController(FruitController fruitController) {
        this.fruitController = fruitController;
        loadFruits();
    }

    public void loadFruits() {
        int column = 0;
        int row = 0;

        for(Fruit fruit : fruitController.getFruits()) {
            Button btn = new Button(fruit.getName());
            btn.setPrefWidth(150);
            btn.setPrefHeight(100);

            itemsGrid.add(btn, column, row);

            column++;

            if (column == 3) {
                column = 0;
                row++;
            }
        }
    }

}