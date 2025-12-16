package com.groep3.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.collections.FXCollections;
import javafx.scene.layout.VBox;

import java.io.IOException;

import com.groep3.App;
import com.groep3.model.Fruit;
import com.groep3.views.FruitCard;

public class MainScreenController {

    @FXML private GridPane fruitItems;
    @FXML private TextField searchBar;
    @FXML private ListView<String> winkelmandList;
    @FXML private Label total;
    @FXML private Button afrekenButton;

    private FruitCard fruitCard;
    private FruitController fruitController;
    private ShoppingCartController shoppingCartController;
    private ProductPopupController productPopupController;
    private SearchBarController searchBarController;

    public void setFruitController(FruitController fruitController, ShoppingCartController shoppingCartController) {
        this.fruitController = fruitController;
        this.shoppingCartController = shoppingCartController;
        this.productPopupController = new ProductPopupController();

        this.fruitCard = new FruitCard(shoppingCartController, productPopupController, total, winkelmandList);
        this.searchBarController = new SearchBarController(searchBar, fruitController, fruitCard, fruitItems);

        loadFruits();
        shoppingCartController.initShoppingCart(total, winkelmandList);
        searchBarController.initSearchBar();
    }

    private void loadFruits() {
        int column = 0;
        int row = 0;

        for (Fruit fruit : fruitController.getFruits()) {
            VBox card = fruitCard.getFruitCard(fruit);
            fruitItems.add(card, column, row);

            column++;
            if (column == 4) { column = 0; row++; }
        }
    }

    @FXML
    public void checkout() throws IOException {
        App.setRoot("order");
    }
}
