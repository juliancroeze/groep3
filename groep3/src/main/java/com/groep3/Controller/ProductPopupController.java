package com.groep3.Controller;

import com.groep3.Model.Fruit;
import com.groep3.App;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ProductPopupController {

    @FXML private ImageView fruitImage;
    @FXML private Label fruitName;
    @FXML private Label fruitDescription;
    @FXML private Label fruitPrice;

    @FXML private TextField aantalInput;
    @FXML private Button plusBtn;
    @FXML private Button minusBtn;
    @FXML private Button addToCartBtn;
    @FXML private Button closeBtn;

    private Fruit fruit;
    private Stage stage;
    private ShoppingCartController shoppingCart;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    // check of fruit al bestaat zoja laad data
    public void setShoppingCart(ShoppingCartController cart) {
        this.shoppingCart = cart;
        if (this.fruit != null) {
            loadFruitData();
        }
    }


    public void setFruit(Fruit fruit) {
        this.fruit = fruit;
        if (this.shoppingCart != null) {
            loadFruitData();
        }
    }

    private void updateMinusButtonVisibility(int amount) {
        minusBtn.setVisible(amount > 0);
    }

    private int safeParse(String text) {
        try {
            return Integer.parseInt(text.trim());
        } catch (Exception e) {
            return 0;
        }
    }

    private void loadFruitData() {
        fruitName.setText(fruit.getName());
        fruitDescription.setText(fruit.getDescription());
        fruitPrice.setText(String.format("€ %.2f", fruit.getPrice()));

        if (fruit.getImagePath() != null) {
            try {
                Image img = new Image(App.class.getResourceAsStream(fruit.getImagePath()));
                fruitImage.setImage(img);
            } catch (Exception ignored) {}
        }

        int amount = shoppingCart.getCartItems().getOrDefault(fruit, 0);

        aantalInput.setText(String.valueOf(amount));
        updateMinusButtonVisibility(amount);
    }

    @FXML
    private void initialize() {

        plusBtn.setOnAction(e -> {
            int cur = safeParse(aantalInput.getText());
            cur++;
            aantalInput.setText(String.valueOf(cur));
            updateMinusButtonVisibility(cur);
        });

        minusBtn.setOnAction(e -> {
            int cur = safeParse(aantalInput.getText());
            if (cur > 0) cur--;
            aantalInput.setText(String.valueOf(cur));
            updateMinusButtonVisibility(cur);
        });

        addToCartBtn.setOnAction(e -> {
            int amount = safeParse(aantalInput.getText());

            // 🔥 update mandje
            shoppingCart.setAmount(fruit, amount);

            stage.close();
        });

        closeBtn.setOnAction(e -> stage.close());
    }
}
