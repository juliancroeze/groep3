package com.groep3.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.groep3.model.Fruit;

import javafx.collections.FXCollections;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class ShoppingCartController {
    private HashMap<Fruit, Integer> cartItems;

    public ShoppingCartController() {
        cartItems = new HashMap<Fruit, Integer>();

    }

    public void initShoppingCart(Label total, ListView<String> winkelmandList) {
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

    public void updateShoppingCart(ListView<String> winkelmandList) {
        List<String> itemNames = new ArrayList<>();
        for (Map.Entry<Fruit, Integer> entry : getCartItems().entrySet()) {
            Fruit fruit = entry.getKey();
            int count = entry.getValue();
            double totalPrice = fruit.getPrice() * count;

            itemNames.add(fruit.getName() + " x" + count + " - €" + String.format("%.2f", totalPrice));
        }

        winkelmandList.setItems(FXCollections.observableArrayList(itemNames));
    }

}
