package com.groep3.controller;

import com.groep3.model.Fruit;
import com.groep3.views.FruitCard;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public abstract class FruitFilter {
    protected FilterController filterController;
    protected FruitController fruitController;
    protected FruitCard fruitCard;
    protected GridPane fruitItems;

    public FruitFilter(FilterController filterController, FruitController fruitController, FruitCard fruitCard, GridPane fruitItems) {
        this.filterController = filterController;
        this.fruitController = fruitController;
        this.fruitCard = fruitCard;
        this.fruitItems = fruitItems;
    }

    public abstract void apply();
}