package com.groep3.views;

import com.groep3.controller.ShoppingCartController;
import com.groep3.model.Product;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class ShoppingCartItem {

    private final ShoppingCartController controller;
    private final Label totalLabel;
    private final Runnable refreshCallback;

    public ShoppingCartItem(ShoppingCartController controller, Label totalLabel, Runnable refreshCallback) {
        this.controller = controller;
        this.totalLabel = totalLabel;
        this.refreshCallback = refreshCallback;
    }

    public HBox createCartItem(Product product) {
        int quantity = controller.getCartItems().getOrDefault(product, 0);
        if (quantity <= 0) {
            return null;
        }

        HBox cartItem = new HBox(20);
        cartItem.setAlignment(Pos.CENTER_LEFT);
        cartItem.setPrefHeight(60);

        VBox nameAmount = new VBox();

        Label nameLabel = new Label(product.getName());

        HBox quantityBox = new HBox(8);
        quantityBox.setAlignment(Pos.CENTER_LEFT);

        Button minusBtn = new Button("-");

        Label quantityLabel = new Label(String.valueOf(quantity));

        Button plusBtn = new Button("+");

        quantityBox.getChildren().addAll(minusBtn, quantityLabel, plusBtn);

        nameAmount.getChildren().addAll(nameLabel, quantityBox);

        double itemTotal = product.getPrice() * quantity;
        Label priceLabel = new Label("€ " + String.format("%.2f", itemTotal));
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        cartItem.getChildren().addAll(nameAmount, spacer, priceLabel);

        plusBtn.setOnAction(e -> {
            controller.add(product);
            updateDisplays(product, quantityLabel, priceLabel);
            if (refreshCallback != null)
                refreshCallback.run();
        });

        minusBtn.setOnAction(e -> {
            controller.remove(product);
            updateDisplays(product, quantityLabel, priceLabel);
            if (refreshCallback != null)
                refreshCallback.run();
        });

        return cartItem;
    }

    private void updateDisplays(Product product, Label quantityLabel, Label priceLabel) {
        int qty = controller.getCartItems().getOrDefault(product, 0);
        quantityLabel.setText(String.valueOf(qty));

        double itemTotal = product.getPrice() * qty;
        priceLabel.setText("€ " + String.format("%.2f", itemTotal));

        totalLabel.setText("Total: € " + String.format("%.2f", controller.getTotal()));
    }
}