package com.groep3.Controller;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.collections.FXCollections;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.io.IOException;

import com.groep3.App;
import com.groep3.Model.Fruit;

public class MainScreenController {

    @FXML private GridPane fruitItems;
    @FXML private TextField searchBar;
    @FXML private ListView<String> winkelmandList;
    @FXML private Label total;
    @FXML private Button afrekenButton;

    private FruitController fruitController;
    private ShoppingCartController shoppingCartController;

    public void setFruitController(FruitController fruitController, ShoppingCartController shoppingCartController) {
        this.fruitController = fruitController;
        this.shoppingCartController = shoppingCartController;
        loadFruits();
        initializeWinkelmand();
        init();
    }

    private void init() {
        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            fruitItems.getChildren().clear();

            int column = 0;
            int row = 0;

            for (Fruit fruit : fruitController.getFruits()) {
                if (fruit.getName().toLowerCase().contains(newValue.toLowerCase())) {
                    VBox card = fruitBox(fruit);
                    fruitItems.add(card, column, row);

                    column++;
                    if (column == 3) { column = 0; row++; }
                }
            }
        });
    }

    private void loadFruits() {
        int column = 0;
        int row = 0;

        for (Fruit fruit : fruitController.getFruits()) {
            VBox card = fruitBox(fruit);
            fruitItems.add(card, column, row);

            column++;
            if (column == 3) { column = 0; row++; }
        }
    }

    private VBox fruitBox(Fruit fruit) {
    VBox fruitContainer = new VBox(10);
    fruitContainer.setPrefSize(240, 360);
    fruitContainer.getStyleClass().add("fruit-card");
    
    fruitContainer.setOnMouseClicked(e -> openPopup(fruit));

    VBox imgBox = new VBox();
    imgBox.getStyleClass().add("fruit-image-box");
    imgBox.setPrefSize(140, 140);

    if (fruit.getImagePath() != null) {
        Image image = new Image(
                App.class.getResourceAsStream(fruit.getImagePath()),
                120, 120, true, true
        );
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(false);
        imageView.fitWidthProperty().bind(imgBox.widthProperty());
        imageView.fitHeightProperty().bind(imgBox.heightProperty());
        imgBox.getChildren().add(imageView);
    } else {
        Label noImg = new Label("No Image");
        noImg.setStyle("-fx-text-fill: #888; -fx-font-size: 14px;");
        imgBox.getChildren().add(noImg);
    }

    VBox infoContainer = new VBox(4);
    infoContainer.setAlignment(Pos.CENTER_LEFT);

    HBox buttonContainer = new HBox(6);


    Label name = new Label(fruit.getName());
    name.getStyleClass().add("fruit-name-label");

    Label description = new Label(fruit.getDescription());
    description.getStyleClass().add("fruit-description-label");
    description.setWrapText(true);
    description.setMaxWidth(200);

    Label price = new Label("€ " + String.format("%.2f", fruit.getPrice()));
    price.getStyleClass().add("fruit-price-label");

    Button removeButton = new Button("-");
    removeButton.getStyleClass().add("fruit-remove-button");
    

    removeButton.setOnAction(e -> {
        shoppingCartController.remove(fruit);
        updateShoppingCart();
        total.setText("Total: € " + String.format("%.2f", shoppingCartController.getTotal()));
    });

    Button addButton = new Button("+");
    addButton.getStyleClass().add("fruit-add-button");

    addButton.setOnAction(e -> {
        shoppingCartController.add(fruit);
        updateShoppingCart();
        total.setText("Total: € " + String.format("%.2f", shoppingCartController.getTotal()));
    });

    infoContainer.getChildren().addAll(name, description, price);
    buttonContainer.getChildren().addAll(removeButton, addButton);
    fruitContainer.getChildren().addAll(imgBox, infoContainer, buttonContainer);

    return fruitContainer;
}

    private void updateShoppingCart() {
        List<String> itemNames = new ArrayList<>();
        for (Map.Entry<Fruit, Integer> entry : shoppingCartController.getCartItems().entrySet()) {
            Fruit fruit = entry.getKey();
            int count = entry.getValue();
            double totalPrice = fruit.getPrice() * count;

            itemNames.add(fruit.getName() + " x" + count + " - €" + String.format("%.2f", totalPrice));
        }

        winkelmandList.setItems(FXCollections.observableArrayList(itemNames));
    }

    @FXML
    public void checkout() throws IOException {
        App.setRoot("order");
    }

    public void initializeWinkelmand() {
        total.setText("Total: € " + String.format("%.2f", shoppingCartController.getTotal()));
        winkelmandList.setItems(FXCollections.observableArrayList());
    }

    private void openPopup(Fruit fruit) {
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

            updateShoppingCart();
            total.setText("Total: € " + String.format("%.2f", shoppingCartController.getTotal()));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
