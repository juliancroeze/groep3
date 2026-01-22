package com.groep3.controller;

import com.groep3.model.Product;
import com.groep3.views.ShoppingCartItem;

import javafx.collections.FXCollections;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCartController {

    private HashMap<Product, Integer> cartItems = new HashMap<>();

    public ShoppingCartController() {
    }

    public void initShoppingCart(Label total, ListView<HBox> winkelmandList) {
        total.setText("Total: € " + String.format("%.2f", getTotal()));
        winkelmandList.setItems(FXCollections.observableArrayList());
    }

    public HashMap<Product, Integer> getCartItems() {
        return cartItems;
    }

    public double getTotal() {
        double sum = 0.0;
        for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            sum += product.getPrice() * quantity;
        }
        return sum;
    }

    public void add(Product product) {
        cartItems.put(product, cartItems.getOrDefault(product, 0) + 1);
    }

    public void remove(Product product) {
        if (cartItems.containsKey(product)) {
            int count = cartItems.get(product);
            if (count > 1) {
                cartItems.put(product, count - 1);
            } else {
                cartItems.remove(product);
            }
        }
    }

    public void setAmount(Product product, int amount) {
        if (amount <= 0) {
            cartItems.remove(product);
        } else {
            cartItems.put(product, amount);
        }
    }

    public void updateShoppingCart(ListView<HBox> winkelmandList, Label total) {
        List<HBox> items = new ArrayList<>();

        ShoppingCartItem cartItemView = new ShoppingCartItem(this, total, () -> updateShoppingCart(winkelmandList, total));

        for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
            Product product = entry.getKey();
            HBox item = cartItemView.createCartItem(product);
            if (item != null) {
                items.add(item);
            }
        }

        winkelmandList.setItems(FXCollections.observableArrayList(items));
        total.setText("Total: € " + String.format("%.2f", getTotal()));
    }
}