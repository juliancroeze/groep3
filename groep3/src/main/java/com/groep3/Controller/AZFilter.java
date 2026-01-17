package com.groep3.controller;

import com.groep3.views.FruitCard;
import javafx.scene.layout.GridPane;

public class AZFilter extends FruitFilter {
    public AZFilter(FilterController filterController, FruitController fruitController, FruitCard fruitCard, GridPane fruitItems) {
        super(filterController, fruitController, fruitCard, fruitItems);
    }

    @Override
    public void apply() {
        fruitController.getFruits().sort((f1, f2) -> f1.getName().compareToIgnoreCase(f2.getName()));
        filterController.updateFruitItems();
    }
}