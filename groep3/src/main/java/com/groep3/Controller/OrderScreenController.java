package com.groep3.Controller;
import com.groep3.App;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class OrderScreenController {

    @FXML
    private VBox orderOptions;

    @FXML
    private void returnToMain() throws Exception {
        App.setRoot("mainscreen");
    }

    @FXML
    private void orderOption() {
        hideButtons();
    }

    @FXML
    private void takeawayOption() {
        hideButtons();
    }

    private void hideButtons() {
        orderOptions.setVisible(false);
    }
}
