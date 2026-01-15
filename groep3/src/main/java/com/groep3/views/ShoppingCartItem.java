package com.groep3.views;

import com.groep3.controller.ShoppingCartController;
import com.groep3.model.Fruit;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class ShoppingCartItem {

    private final ShoppingCartController controller;
    private final Label totalLabel;
    private final Runnable refreshCallback;

    public ShoppingCartItem(ShoppingCartController controller, Label totalLabel, Runnable refreshCallback) {
        this.controller = controller;
        this.totalLabel = totalLabel;
        this.refreshCallback = refreshCallback;
    }

    public HBox createCartItem(Fruit fruit) {
        int quantity = controller.getCartItems().getOrDefault(fruit, 0);
        if (quantity <= 0) {
            return null;
        }

        HBox cartItem = new HBox(20);
        cartItem.setAlignment(Pos.CENTER_LEFT);
        cartItem.setPrefHeight(60);

        Label nameLabel = new Label(fruit.getName());

        HBox quantityBox = new HBox(8);
        quantityBox.setAlignment(Pos.CENTER);

        Button minusBtn = new Button("-");

        Label quantityLabel = new Label(String.valueOf(quantity));

        Button plusBtn = new Button("+");

        quantityBox.getChildren().addAll(minusBtn, quantityLabel, plusBtn);

        double itemTotal = fruit.getPrice() * quantity;
        Label priceLabel = new Label("€ " + String.format("%.2f", itemTotal));
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        cartItem.getChildren().addAll(nameLabel, quantityBox, spacer, priceLabel);

        plusBtn.setOnAction(e -> {
            controller.add(fruit);
            updateDisplays(fruit, quantityLabel, priceLabel);
            if (refreshCallback != null)
                refreshCallback.run();
        });

        minusBtn.setOnAction(e -> {
            controller.remove(fruit);
            updateDisplays(fruit, quantityLabel, priceLabel);
            if (refreshCallback != null)
                refreshCallback.run();
        });

        return cartItem;
    }

    private void updateDisplays(Fruit fruit, Label quantityLabel, Label priceLabel) {
        int qty = controller.getCartItems().getOrDefault(fruit, 0);
        quantityLabel.setText(String.valueOf(qty));

        double itemTotal = fruit.getPrice() * qty;
        priceLabel.setText("€ " + String.format("%.2f", itemTotal));

        totalLabel.setText("Total: € " + String.format("%.2f", controller.getTotal()));
    }
}