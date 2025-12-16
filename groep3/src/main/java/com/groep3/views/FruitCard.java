package com.groep3.views;

import com.groep3.App;
import com.groep3.controller.ProductPopupController;
import com.groep3.controller.ShoppingCartController;
import com.groep3.model.Fruit;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class FruitCard {

    private ShoppingCartController shoppingCartController;
    private ProductPopupController productPopupController;
    private ListView<String> winkelmandList;
    private Label total;

    public FruitCard(ShoppingCartController shoppingCartController, ProductPopupController productPopupController, Label total, ListView<String> winkelmandList) {
        this.shoppingCartController = shoppingCartController;
        this.productPopupController = productPopupController;
        this.winkelmandList = winkelmandList;
        this.total = total;

    }

    public VBox getFruitCard(Fruit fruit) {
        double fruitContainerHeiht = 191.65;
        double fruitContainerWidth = 221.80;

        VBox fruitContainer = new VBox(5);
        fruitContainer.setPrefSize(fruitContainerWidth, fruitContainerHeiht);
        fruitContainer.getStyleClass().add("fruit-card");

        fruitContainer.setOnMouseClicked(e -> productPopupController.openPopup(fruit, shoppingCartController, winkelmandList, total));

        VBox imgBox = new VBox();

        imgBox.setPrefSize(fruitContainerWidth, 89);
        Rectangle clip = new Rectangle(fruitContainerWidth, fruitContainerHeiht);
        clip.setArcWidth(44);
        clip.setArcHeight(44);

        imgBox.setClip(clip);

        if (fruit.getImagePath() != null) {
            Image image = new Image(
                    App.class.getResourceAsStream(fruit.getImagePath()),
                    fruitContainerWidth, 89, true, true);
            imgBox.getStyleClass().add("fruit-image-box");
            ImageView imageView = new ImageView(image);
            imageView.fitWidthProperty().bind(imgBox.widthProperty());
            imageView.fitHeightProperty().bind(imgBox.heightProperty());
            imgBox.getChildren().add(imageView);
        } else {
            Label noImg = new Label("No Image");
            noImg.setStyle("-fx-text-fill: #888; -fx-font-size: 14px;");
            imgBox.getChildren().add(noImg);
        }

        VBox infoContainer = new VBox(4);
        infoContainer.setAlignment(Pos.TOP_LEFT);
        infoContainer.setPrefSize(fruitContainerWidth, 102);

        HBox buttonContainer = new HBox(6);

        Label name = new Label(fruit.getName());
        name.getStyleClass().add("label");

        Label description = new Label(fruit.getDescription());
        description.getStyleClass().add("fruit-description-label");
        description.setWrapText(true);

        Label price = new Label("€ " + String.format("%.2f", fruit.getPrice()));
        price.getStyleClass().add("fruit-price-label");

        Button removeButton = new Button("-");
        removeButton.getStyleClass().add("fruit-remove-button");

        removeButton.setOnAction(e -> {
            shoppingCartController.remove(fruit);
            shoppingCartController.updateShoppingCart(winkelmandList);
            total.setText("Total: € " + String.format("%.2f", shoppingCartController.getTotal()));
        });

        Button addButton = new Button("+");
        addButton.getStyleClass().add("fruit-add-button");

        addButton.setOnAction(e -> {
            shoppingCartController.add(fruit);
            shoppingCartController.updateShoppingCart(winkelmandList);
            total.setText("Total: € " + String.format("%.2f", shoppingCartController.getTotal()));
        });

        infoContainer.getChildren().addAll(name, description, price);
        buttonContainer.getChildren().addAll(removeButton, addButton);
        fruitContainer.getChildren().addAll(imgBox, infoContainer, buttonContainer);

        return fruitContainer;
    }
}
