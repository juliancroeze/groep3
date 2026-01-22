package com.groep3.controller;

import com.groep3.views.FruitCard;
import javafx.scene.layout.GridPane;

public class PriceLowHighFilter extends FruitFilter {
    public PriceLowHighFilter(FilterController filterController, ProductController productController, FruitCard fruitCard, GridPane fruitItems) {
        super(filterController, productController, fruitCard, fruitItems);
    }

    @Override
    public void apply() {
        productController.getProducts().sort((f1, f2) -> Double.compare(f1.getPrice(), f2.getPrice()));
        filterController.updateFruitItems();
    }
}