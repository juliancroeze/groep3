package com.groep3.controller;

import com.groep3.model.Fruit;
import com.groep3.model.FruitBasket;
import com.groep3.model.Product;
import com.groep3.views.BasketCard;
import com.groep3.views.FruitCard;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class FilterController {

    private ProductController productController;
    private FruitCard fruitCard;
    private BasketCard basketCard;
    private GridPane fruitItems;

    private AZFilter azFilter;
    private ZAFilter zaFilter;
    private PriceLowHighFilter priceLowHighFilter;
    private PriceHighLowFilter priceHighLowFilter;

    public void init(ProductController productController, FruitCard fruitCard, BasketCard basketCard, GridPane fruitItems) {
        this.productController = productController;
        this.fruitCard = fruitCard;
        this.basketCard = basketCard;
        this.fruitItems = fruitItems;

        this.azFilter = new AZFilter(this, productController, fruitCard, fruitItems);
        this.zaFilter = new ZAFilter(this, productController, fruitCard, fruitItems);
        this.priceLowHighFilter = new PriceLowHighFilter(this, productController, fruitCard, fruitItems);
        this.priceHighLowFilter = new PriceHighLowFilter(this, productController, fruitCard, fruitItems);
    }

    public void updateFruitItems() {
        fruitItems.getChildren().clear();
        int column = 0;
        int row = 0;

        for (Product product : productController.getProducts()) {
            VBox card;

            if (product instanceof FruitBasket) {
                FruitBasket basket = (FruitBasket) product;
                card = basketCard.getBasketCard(basket);
            } else {
                Fruit fruit = (Fruit) product;
                card = fruitCard.getFruitCard(fruit);
            }

            fruitItems.add(card, column, row);
            column++;
            if (column == 4) {
                column = 0;
                row++;
            }
        }
    }

    public void applyFilterAZ() { azFilter.apply(); }
    public void applyFilterZA() { azFilter.apply(); }
    public void applyFilterPriceLowHigh() { priceLowHighFilter.apply(); }
    public void applyFilterPriceHighLow() { priceHighLowFilter.apply(); }
}