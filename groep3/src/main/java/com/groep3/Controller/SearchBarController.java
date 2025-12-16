package com.groep3.controller;

import com.groep3.model.Fruit;
import com.groep3.views.FruitCard;

import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class SearchBarController {
    private TextField searchBar;
    private FruitController fruitController;
    private FruitCard fruitCard;
    private GridPane fruitItems;

    public SearchBarController(TextField searchBar, FruitController fruitController, FruitCard fruitCard,
            GridPane fruitItems) {
        this.searchBar = searchBar;
        this.fruitController = fruitController;
        this.fruitCard = fruitCard;
        this.fruitItems = fruitItems;
    }

    public void initSearchBar() {
        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            fruitItems.getChildren().clear();

            int column = 0;
            int row = 0;

            for (Fruit fruit : fruitController.getFruits()) {
                if (fruit.getName().toLowerCase().contains(newValue.toLowerCase())) {
                    VBox card = fruitCard.getFruitCard(fruit);
                    fruitItems.add(card, column, row);

                    column++;
                    if (column == 4) {
                        column = 0;
                        row++;
                    }
                }
            }
        });
    }
}
