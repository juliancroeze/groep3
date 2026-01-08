package com.groep3.controller;

import com.groep3.model.Fruit;
import com.groep3.views.FruitCard;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox; 

public class FilterController {
    private FruitController fruitController;
    private FruitCard fruitCard;
    private GridPane fruitItems; 

 



    // Initialize with references to the existing screen objects
    public void init(FruitController fruitController, FruitCard fruitCard, GridPane fruitItems) {
        this.fruitController = fruitController;
        this.fruitCard = fruitCard;
        this.fruitItems = fruitItems;
    }


 

    public void applyFilterAZ() {
        fruitController.getFruits().sort((f1, f2) -> f1.getName().compareToIgnoreCase(f2.getName()));
        fruitItems.getChildren().clear();
        int column = 0;
        int row = 0;
        for (Fruit fruit : fruitController.getFruits()) {
            VBox card = fruitCard.getFruitCard(fruit);
            fruitItems.add(card, column, row);
            column++;
            if (column == 4) { column = 0; row++; }
        }
    }
       

 

    public void applyFilterZA() {
        fruitController.getFruits().sort((f1, f2) -> f2.getName().compareToIgnoreCase(f1.getName()));
        fruitItems.getChildren().clear();
        int column = 0;
        int row = 0;
        for (Fruit fruit : fruitController.getFruits()) {
            VBox card = fruitCard.getFruitCard(fruit);
            fruitItems.add(card, column, row);
            column++;
            if (column == 4) { column = 0; row++; }
        }
    }

 

    public void applyFilterPriceLowHigh() {
        fruitController.getFruits().sort((f1, f2) -> Double.compare(f1.getPrice(), f2.getPrice()));
        fruitItems.getChildren().clear();
        int column = 0;
        int row = 0;
        for (Fruit fruit : fruitController.getFruits()) {
            VBox card = fruitCard.getFruitCard(fruit);
            fruitItems.add(card, column, row);
            column++;
            if (column == 4) { column = 0; row++; }
        }
    }

 

    public void applyFilterPriceHighLow() {
        fruitController.getFruits().sort((f1, f2) -> Double.compare(f2.getPrice(), f1.getPrice()));
        fruitItems.getChildren().clear();
        int column = 0;
        int row = 0;
        for (Fruit fruit : fruitController.getFruits()) {
            VBox card = fruitCard.getFruitCard(fruit);
            fruitItems.add(card, column, row);
            column++;
            if (column == 4) { column = 0; row++; }
        }
    }

} 

 





 

 