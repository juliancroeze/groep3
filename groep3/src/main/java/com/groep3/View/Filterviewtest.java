package com.groep3.View;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Popup;

public class Filterviewtest {
    public class MainScreenController {

    @FXML private Button filterButton;

    @FXML
    private void initialize() {
        filterButton.setOnAction(e -> openFilterPopup());
        }
    private void openFilterPopup() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FilterPopupController.fxml"));
            Parent popupRoot = loader.load();          
            FilterPopupController controller = loader.getController();

            Popup popup = new Popup();
            popup.getContent().add(popupRoot);
            popup.show(filterButton.getScene().getWindow(),
                       filterButton.localToScene(filterButton.getBoundsInLocal()).getMinX() +
                       filterButton.getScene().getX() + filterButton.getScene().getWindow().getX(),
                       filterButton.localToScene(filterButton.getBoundsInLocal()).getMaxY() +
                       filterButton.getScene().getY() + filterButton.getScene().getWindow().getY());

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    } 
}
}
