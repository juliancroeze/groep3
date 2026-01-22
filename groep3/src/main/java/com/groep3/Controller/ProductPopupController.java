package com.groep3.controller;

import com.groep3.App;
import com.groep3.model.Fruit;
import com.groep3.model.FruitBasket;
import com.groep3.model.FruitDeal;
import com.groep3.model.OrganicFruitBasket;
import com.groep3.model.Product;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ProductPopupController {

    @FXML
    private ImageView fruitImage;
    @FXML
    private Label fruitName;
    @FXML
    private Label fruitPrice;
    @FXML
    private Label fruitBoer;
    @FXML
    private Label fruitOrigin;
    @FXML
    private Label fruitCategory;
    @FXML
    private Label fruitType;
    @FXML
    private Label fruitStock;
    @FXML
    private Label fruitFarm;
    @FXML
    private Label fruitRegion;
    @FXML
    private Label fruitDescription;
    @FXML
    private Label fruitMessage;

    @FXML
    private TextField aantalInput;
    @FXML
    private Button plusBtn;
    @FXML
    private Button minusBtn;
    @FXML
    private Button addToCartBtn;
    @FXML
    private Button closeBtn;

    private Product product;
    private Stage stage;
    private ShoppingCartController shoppingCart;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setShoppingCart(ShoppingCartController cart) {
        this.shoppingCart = cart;
        if (this.product != null) {
            loadProductData();
        }
    }

    public void setProduct(Product product) {
        this.product = product;
        if (this.shoppingCart != null) {
            loadProductData();
        }
    }

    private int safeParse(String text) {
        try {
            return Integer.parseInt(text.trim());
        } catch (Exception e) {
            return 0;
        }
    }

    private void updateMinusButtonVisibility(int amount) {
        minusBtn.setVisible(amount > 0);
    }

    private void loadProductData() {
        fruitName.setText(product.getName());
        fruitDescription.setText(product.getDescription());

        String priceText = "€ " + String.format("%.2f", product.getPrice());

        if (product instanceof FruitDeal) {
            FruitDeal deal = (FruitDeal) product;
            priceText += " (was € " + String.format("%.2f", deal.getOriginalPrice()) + ")";
        }
        if (product instanceof OrganicFruitBasket) {
            priceText += " (incl. bio-toeslag)";
        }
        fruitPrice.setText(priceText);

        fruitOrigin.setText("");
        fruitCategory.setText("");
        fruitType.setText("");
        fruitStock.setText("");
        fruitFarm.setText("");
        fruitRegion.setText("");
        fruitBoer.setText("");
        fruitMessage.setText("");

        if (product instanceof Fruit) {
            Fruit fruit = (Fruit) product;
            fruitOrigin.setText(fruit.getHerkomst() != null ? "Herkomst: " + fruit.getHerkomst() : "");
            fruitCategory.setText(fruit.getCategorie() != null ? "Categorie: " + fruit.getCategorie() : "");
            fruitType.setText(fruit.getSoort() != null ? "Soort: " + fruit.getSoort() : "");
            fruitStock.setText(fruit.getBeschikbaarheid() > 0 ? "Beschikbaar: " + fruit.getBeschikbaarheid() : "");
            fruitFarm.setText(fruit.getBoerderij() != null ? "Boerderij: " + fruit.getBoerderij() : "");
            fruitRegion.setText(fruit.getStreek() != null ? "Streek: " + fruit.getStreek() : "");
            fruitBoer.setText(fruit.getProducer() != null ? "Boer: " + fruit.getProducer() : "");
            fruitMessage.setText(fruit.getBoerBericht() != null ? fruit.getBoerBericht() : "");
        }

        if (product instanceof FruitBasket) {
            FruitBasket basket = (FruitBasket) product;
            String contents = "Bevat: " + String.join(", ", basket.getContainedFruits());
            fruitDescription.setText(product.getDescription() + "\n\n" + contents);
            fruitCategory.setText("Fruitmand");
            fruitType.setText(product instanceof OrganicFruitBasket ? "Biologisch" : "Standaard");
        }

        if (product.getImagePath() != null) {
            try {
                Image img = new Image(App.class.getResourceAsStream(product.getImagePath()));
                fruitImage.setImage(img);
            } catch (Exception ignored) {
            }
        }

        int amount = shoppingCart.getCartItems().getOrDefault(product, 0);
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
            if (cur > 0)
                cur--;
            aantalInput.setText(String.valueOf(cur));
            updateMinusButtonVisibility(cur);
        });

        addToCartBtn.setOnAction(e -> {
            int amount = safeParse(aantalInput.getText());
            shoppingCart.setAmount(product, amount);
            stage.close();
        });

        closeBtn.setOnAction(e -> stage.close());
    }

    public void openPopup(Product product, ShoppingCartController shoppingCartController,
            ListView<HBox> winkelmandList, Label total) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("productPopup.fxml"));
            Parent root = loader.load();

            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setScene(new Scene(root));
            popupStage.setResizable(false);

            ProductPopupController controller = loader.getController();
            controller.setStage(popupStage);
            controller.setProduct(product);
            controller.setShoppingCart(shoppingCartController);

            popupStage.showAndWait();

            shoppingCartController.updateShoppingCart(winkelmandList, total);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}