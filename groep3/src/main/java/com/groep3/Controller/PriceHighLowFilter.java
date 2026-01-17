package com.groep3.controller;

import com.groep3.views.FruitCard;
import javafx.scene.layout.GridPane;

public class PriceHighLowFilter extends FruitFilter {
    public PriceHighLowFilter(FilterController filterController, FruitController fruitController, FruitCard fruitCard, GridPane fruitItems) {
        super(filterController, fruitController, fruitCard, fruitItems);
    }

    @Override
    public void apply() {
        fruitController.getFruits().sort((f1, f2) -> Double.compare(f2.getPrice(), f1.getPrice()));
        filterController.updateFruitItems();
    }
}