package com.groep3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import com.groep3.Controller.FruitController;
import com.groep3.Controller.MainScreenController;
import com.groep3.Controller.ShoppingCartController;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    public static FruitController fruitController = new FruitController();
    public static ShoppingCartController shoppingCartController = new ShoppingCartController();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("mainscreen.fxml"));
        Parent root = loader.load();

        MainScreenController controller = loader.getController();
        controller.setFruitController(fruitController, shoppingCartController);

        scene = new Scene(root, 1280, 720);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        Parent root = loader.load();

        // Behoud de controller bij het wisselen van scherm
        if (fxml.equals("mainscreen")) {
            MainScreenController c = loader.getController();
            c.setFruitController(fruitController, shoppingCartController);
        }

        scene.setRoot(root);    
    }

    public static void main(String[] args) {
        launch();
    }

}