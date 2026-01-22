package com.groep3.controller;

import com.groep3.model.Fruit;
import com.groep3.model.FruitBasket;
import com.groep3.model.Product;
import com.groep3.views.BasketCard;
import com.groep3.views.FruitCard;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class SearchBarController {

    private TextField searchBar;
    private ProductController productController;
    private FruitCard fruitCard;
    private BasketCard basketCard;
    private GridPane fruitItems;

    public SearchBarController(TextField searchBar, ProductController productController,
            FruitCard fruitCard, BasketCard basketCard, GridPane fruitItems) {
        this.searchBar = searchBar;
        this.productController = productController;
        this.fruitCard = fruitCard;
        this.basketCard = basketCard;
        this.fruitItems = fruitItems;
    }

    public void initSearchBar() {
        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            fruitItems.getChildren().clear();
            int column = 0;
            int row = 0;

            for (Product product : productController.getProducts()) {
                if (product.getName().toLowerCase().contains(newValue.toLowerCase())) {
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
        });
    }
}