package com.groep3.controller;

import com.groep3.views.FruitCard;
import javafx.scene.layout.GridPane;

public class AZFilter extends FruitFilter {
    public AZFilter(FilterController filterController, ProductController productController, FruitCard fruitCard,
            GridPane fruitItems) {
        super(filterController, productController, fruitCard, fruitItems);
    }

    @Override
    public void apply() {
        productController.getProducts().sort((f1, f2) -> f1.getName().compareToIgnoreCase(f2.getName()));
        filterController.updateFruitItems();
    }
}