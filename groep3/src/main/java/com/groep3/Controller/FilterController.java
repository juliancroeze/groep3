package com.groep3.controller;

import com.groep3.model.Fruit;
import com.groep3.views.FruitCard;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox; 

public class FilterController {
    private FruitController fruitController;
    private FruitCard fruitCard;
    private GridPane fruitItems; 

    private AZFilter azFilter;
    private ZAFilter zaFilter;
    private PriceLowHighFilter priceLowHighFilter;
    private PriceHighLowFilter priceHighLowFilter;

    public void init(FruitController fruitController, FruitCard fruitCard, GridPane fruitItems) {
        this.fruitController = fruitController;
        this.fruitCard = fruitCard;
        this.fruitItems = fruitItems;
        this.azFilter = new AZFilter(this, fruitController, fruitCard, fruitItems);
        this.zaFilter = new ZAFilter(this, fruitController, fruitCard, fruitItems);
        this.priceLowHighFilter = new PriceLowHighFilter(this, fruitController, fruitCard, fruitItems);
        this.priceHighLowFilter = new PriceHighLowFilter(this, fruitController, fruitCard, fruitItems);
    }

    public void updateFruitItems() {
        fruitItems.getChildren().clear();
        int column = 0;
        int row = 0;
        for (Fruit fruit : fruitController.getFruits()) {
            VBox card = fruitCard.getFruitCard(fruit);
            fruitItems.add(card, column, row);
            column++;
            if (column == 4){
                column = 0; row++; }
        }
    }

    public void applyFilterAZ() {
        azFilter.apply();
    }

    public void applyFilterZA() {
        zaFilter.apply();
    }

    public void applyFilterPriceLowHigh() {
        priceLowHighFilter.apply();
    }

    public void applyFilterPriceHighLow() {
        priceHighLowFilter.apply();
    }

} 

 





 

 