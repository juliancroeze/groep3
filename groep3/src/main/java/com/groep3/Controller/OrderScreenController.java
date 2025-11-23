package com.groep3.Controller;
import com.groep3.App;

import javafx.fxml.FXML;

public class OrderScreenController {
    @FXML
    private void returnToMain() throws Exception {
        App.setRoot("mainscreen");
    }
}
