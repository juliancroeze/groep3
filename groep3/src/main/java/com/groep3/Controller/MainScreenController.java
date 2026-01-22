package com.groep3.controller;

import com.groep3.App;
import com.groep3.model.Fruit;
import com.groep3.model.FruitBasket;
import com.groep3.model.Product;
import com.groep3.views.BasketCard;
import com.groep3.views.FruitCard;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MainScreenController {

    @FXML private GridPane fruitItems;
    @FXML private TextField searchBar;
    @FXML private ListView<HBox> winkelmandList;
    @FXML private Label total;
    @FXML private Button afrekenButton;
    @FXML private MenuButton filterButton;

    private ProductController productController;
    private ShoppingCartController shoppingCartController;
    private ProductPopupController productPopupController;
    private SearchBarController searchBarController;
    private FilterController filterController;

    private FruitCard fruitCard;
    private BasketCard basketCard;

    public void setProductController(ProductController productController, ShoppingCartController shoppingCartController) {
        this.productController = productController;
        this.shoppingCartController = shoppingCartController;

        this.productPopupController = new ProductPopupController();

        this.fruitCard = new FruitCard(shoppingCartController, productPopupController, total, winkelmandList);
        this.basketCard = new BasketCard(shoppingCartController, productPopupController, total, winkelmandList);

        this.searchBarController = new SearchBarController(searchBar, productController, fruitCard, basketCard, fruitItems);
        this.filterController = new FilterController();
        this.filterController.init(productController, fruitCard, basketCard, fruitItems);

        loadProducts();
        shoppingCartController.initShoppingCart(total, winkelmandList);
        searchBarController.initSearchBar();
    }

    private void loadProducts() {
        fruitItems.getChildren().clear();
        int column = 0;
        int row = 0;

        for (Product product : productController.getProducts()) {
            VBox card = createCard(product);
            fruitItems.add(card, column, row);

            column++;
            if (column == 4) {
                column = 0;
                row++;
            }
        }
    }

    private VBox createCard(Product product) {
        if (product instanceof FruitBasket) {
            FruitBasket basket = (FruitBasket) product;
            return basketCard.getBasketCard(basket);
        } else {
            Fruit fruit = (Fruit) product;
            return fruitCard.getFruitCard(fruit);
        }
    }

    public FilterController getFilterController() {
        return filterController;
    }

    @FXML
    public void checkout() throws IOException {
        App.setRoot("order");
    }
}