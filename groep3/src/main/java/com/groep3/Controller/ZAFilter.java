package com.groep3.controller;

import com.groep3.views.FruitCard;
import javafx.scene.layout.GridPane;

public class ZAFilter extends FruitFilter {
    public ZAFilter(FilterController filterController, ProductController productController, FruitCard fruitCard, GridPane fruitItems) {
        super(filterController, productController, fruitCard, fruitItems);
    }

    @Override
    public void apply() {
        productController.getProducts().sort((f1, f2) -> f2.getName().compareToIgnoreCase(f1.getName()));
        filterController.updateFruitItems();
    }
}