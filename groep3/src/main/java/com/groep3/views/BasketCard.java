package com.groep3.views;

import com.groep3.App;
import com.groep3.controller.ProductPopupController;
import com.groep3.controller.ShoppingCartController;
import com.groep3.model.FruitBasket;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class BasketCard {
    private ShoppingCartController shoppingCartController;
    private ProductPopupController productPopupController;
    private Label total;
    private ListView<HBox> winkelmandList;

    public BasketCard(ShoppingCartController shoppingCartController, ProductPopupController productPopupController,
            Label total, ListView<HBox> winkelmandList) {
        this.shoppingCartController = shoppingCartController;
        this.productPopupController = productPopupController;
        this.winkelmandList = winkelmandList;
        this.total = total;
    }

    public VBox getBasketCard(FruitBasket fruitBasket) {
        double fruitContainerHeight = 191.65;
        double fruitContainerWidth = 221.80;

        VBox fruitContainer = new VBox(5);
        fruitContainer.setPrefSize(fruitContainerWidth, fruitContainerHeight);
        fruitContainer.getStyleClass().add("fruit-card");

        fruitContainer.setOnMouseClicked(
                e -> productPopupController.openPopup(fruitBasket, shoppingCartController, winkelmandList, total));

        VBox imgBox = new VBox();
        imgBox.setPrefSize(fruitContainerWidth, 89);

        Rectangle clip = new Rectangle(fruitContainerWidth, 89);
        clip.setArcWidth(44);
        clip.setArcHeight(44);
        imgBox.setClip(clip);

        if (fruitBasket.getImagePath() != null && !fruitBasket.getImagePath().isEmpty()) {
            try {
                Image image = new Image(
                        App.class.getResourceAsStream(fruitBasket.getImagePath()),
                        fruitContainerWidth, 89, true, true);
                imgBox.getStyleClass().add("fruit-image-box");
                ImageView imageView = new ImageView(image);
                imageView.fitWidthProperty().bind(imgBox.widthProperty());
                imageView.fitHeightProperty().bind(imgBox.heightProperty());
                imgBox.getChildren().add(imageView);
            } catch (Exception ignored) {
                Label noImg = new Label("No Image");
                noImg.setStyle("-fx-text-fill: #888; -fx-font-size: 14px;");
                imgBox.getChildren().add(noImg);
            }
        } else {
            Label noImg = new Label("No Image");
            noImg.setStyle("-fx-text-fill: #888; -fx-font-size: 14px;");
            imgBox.getChildren().add(noImg);
        }

        VBox infoContainer = new VBox(4);
        infoContainer.setAlignment(Pos.TOP_LEFT);
        infoContainer.setPrefSize(fruitContainerWidth, 102);

        HBox buttonContainer = new HBox(6);

        Label name = new Label(fruitBasket.getName());
        name.getStyleClass().add("label");

        Label description = new Label(fruitBasket.getDescription());
        description.getStyleClass().add("fruit-description-label");
        description.setWrapText(true);

        Label price = new Label("€ " + String.format("%.2f", fruitBasket.getPrice()));
        price.getStyleClass().add("fruit-price-label");

        Button removeButton = new Button("-");
        removeButton.getStyleClass().add("fruit-remove-button");
        removeButton.setOnAction(e -> {
            shoppingCartController.remove(fruitBasket);
            shoppingCartController.updateShoppingCart(winkelmandList, total);
            total.setText("Total: € " + String.format("%.2f", shoppingCartController.getTotal()));
        });

        Button addButton = new Button("+");
        addButton.getStyleClass().add("fruit-add-button");
        addButton.setOnAction(e -> {
            shoppingCartController.add(fruitBasket);
            shoppingCartController.updateShoppingCart(winkelmandList, total);
            total.setText("Total: € " + String.format("%.2f", shoppingCartController.getTotal()));
        });

        infoContainer.getChildren().addAll(name, description, price);
        buttonContainer.getChildren().addAll(removeButton, addButton);
        fruitContainer.getChildren().addAll(imgBox, infoContainer, buttonContainer);

        return fruitContainer;
    }
}