package com.groep3.controller;

import com.groep3.App;
import com.groep3.model.Fruit;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ProductPopupController {

    @FXML private ImageView fruitImage;
    @FXML private Label fruitName;
    @FXML private Label fruitBoer;
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

    // check of fruit al bestaat zoja laad data in winkel mandje
    public void setShoppingCart(ShoppingCartController cart) {
        this.shoppingCart = cart;
        if (this.fruit != null) {
            loadFruitData();
        }
    }

    // zet fruit in popup
    public void setFruit(Fruit fruit) {
        this.fruit = fruit;
        if (this.shoppingCart != null) {
            loadFruitData();
        }
    }

    // Stelt het fruit in en laadt de gegevens als deze al beschikbaar zijn
    private void updateMinusButtonVisibility(int amount) {
        minusBtn.setVisible(amount > 0);
    }

    // zorgt dat er geen error komt bij niet geen getal
    private int safeParse(String text) {
        try {
            return Integer.parseInt(text.trim());
        } catch (Exception e) {
            return 0;
        }
    }

    // Laadt de gegevens van het geselecteerde fruit in de popup en toont de huidige hoeveelheid uit het winkelmandje
    private void loadFruitData() {
        fruitName.setText(fruit.getName());
        fruitDescription.setText(fruit.getDescription());
        fruitBoer.setText(fruit.getProducer());
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

        // Plus-knop: verhoogt de hoeveelheid met 1
        plusBtn.setOnAction(e -> {
            int cur = safeParse(aantalInput.getText());
            cur++;
            aantalInput.setText(String.valueOf(cur));
            updateMinusButtonVisibility(cur);
        });

        // Minus-knop: verlaagt de hoeveelheid met 1 (maar niet onder 0)
        minusBtn.setOnAction(e -> {
            int cur = safeParse(aantalInput.getText());
            if (cur > 0) cur--;
            aantalInput.setText(String.valueOf(cur));
            updateMinusButtonVisibility(cur);
        });

        // Add-to-cart knop: slaat de gekozen hoeveelheid op in het winkelmandje en sluit de popup
        addToCartBtn.setOnAction(e -> {
            int amount = safeParse(aantalInput.getText());

            shoppingCart.setAmount(fruit, amount);
            stage.close();
        });

        // Sluit de popup zonder iets te wijzigen
        closeBtn.setOnAction(e -> stage.close());
    }

    public void openPopup(Fruit fruit, ShoppingCartController shoppingCartController, ListView<String> winkelmandList, Label total) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("productPopup.fxml"));
            Parent root = loader.load();

            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setScene(new Scene(root));
            popupStage.setResizable(false);

            ProductPopupController controller = loader.getController();
            controller.setStage(popupStage);
            controller.setFruit(fruit);
            controller.setShoppingCart(shoppingCartController);

            popupStage.showAndWait();

            shoppingCartController.updateShoppingCart(winkelmandList);
            total.setText("Total: € " + String.format("%.2f", shoppingCartController.getTotal()));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
