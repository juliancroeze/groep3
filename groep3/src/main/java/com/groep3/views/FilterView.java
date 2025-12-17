package com.groep3.views;

import java.io.IOException; 
import javafx.fxml.FXML; 
import javafx.fxml.FXMLLoader; 
import javafx.scene.Parent; 
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Popup; 


import com.groep3.controller.FilterController;

public class FilterView {

    @FXML private MenuButton filterButton; 

    @FXML
    private void initialize() { 
        final Image image = new Image(getClass().getResourceAsStream("/com/groep3/FilterIcon.png"), 200, 200, true, true);
        
        ImageView imageView = new ImageView(image);
        filterButton.setGraphic(imageView);
        filterButton.setOnAction(e -> openFilterDropdown()); 
        
        } 

    private void openFilterDropdown() { 
        
        MenuButton menuButton = new MenuButton();
        //menuButton.setGraphic(imageView);
        //menuButton.getItems().addAll(Men,new MenuItem("AZ/ZA"), new MenuItem("Price Low-High/High-Low")); 
    }
} 
