package com.groep3.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.groep3.model.Fruit;
import com.groep3.views.ShoppingCartItem;

import javafx.collections.FXCollections;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

public class ShoppingCartController {
    private HashMap<Fruit, Integer> cartItems;

    public ShoppingCartController() {
        cartItems = new HashMap<Fruit, Integer>();

    }

    public void initShoppingCart(Label total, ListView<HBox> winkelmandList) {
    total.setText("Total: € " + String.format("%.2f", getTotal()));
    winkelmandList.setItems(FXCollections.observableArrayList());
}


    public HashMap<Fruit, Integer> getCartItems() {
        return cartItems;
    }

    public double getTotal() {
        double sum = 0.0;
        for (Fruit fruit : cartItems.keySet()) {
            int quantity = cartItems.get(fruit);
            sum += fruit.getPrice() * quantity;
        }
        return sum;
    }

    public void add(Fruit fruit) {
        cartItems.put(fruit, cartItems.getOrDefault(fruit, 0) + 1);
    }

    public void remove(Fruit fruit) {
        if (cartItems.containsKey(fruit)) {
            int count = cartItems.get(fruit);
            if (count > 1) {
                cartItems.put(fruit, count - 1);
            } else {
                cartItems.remove(fruit);
            }
        }
    }

    public HashMap<Fruit, Integer> getItems() {
        return cartItems;
    }

    public void setAmount(Fruit fruit, int amount) {
        if (amount <= 0) {
            cartItems.remove(fruit);
        } else {
            cartItems.put(fruit, amount);
        }
    }

    public void updateShoppingCart(ListView<HBox> winkelmandList, Label total) {
    List<HBox> items = new ArrayList<>();

    ShoppingCartItem cartItemView =
            new ShoppingCartItem(this, total, () -> updateShoppingCart(winkelmandList, total));

    for (Map.Entry<Fruit, Integer> entry : cartItems.entrySet()) {
        Fruit fruit = entry.getKey();
        HBox item = cartItemView.createCartItem(fruit);
        if (item != null) {
            items.add(item);
        }
    }

    winkelmandList.setItems(FXCollections.observableArrayList(items));
}


}
