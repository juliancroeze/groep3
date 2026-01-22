package com.groep3.controller;

import com.groep3.views.FruitCard;
import javafx.scene.layout.GridPane;

public abstract class FruitFilter {
    protected FilterController filterController;
    protected ProductController productController;
    protected FruitCard fruitCard;
    protected GridPane fruitItems;

    public FruitFilter(FilterController filterController, ProductController productController, FruitCard fruitCard,
            GridPane fruitItems) {
        this.filterController = filterController;
        this.productController = productController;
        this.fruitCard = fruitCard;
        this.fruitItems = fruitItems;
    }

    public abstract void apply();
}