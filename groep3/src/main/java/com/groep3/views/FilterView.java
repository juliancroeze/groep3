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
        final Image filterImage = new Image(getClass().getResourceAsStream("/com/groep3/data/images/FilterIcon.png"), 20, 20, true, true);
        final Image iconAZ = new Image(getClass().getResourceAsStream("/com/groep3/data/images/IconAZ.png"), 20, 20, true, true);
        //final Image iconZA = new Image(getClass().getResourceAsStream("/com/groep3/data/images/IconZA.png"), 20, 20, true, true);
        //final Image iconPriceHighLow = new Image(getClass().getResourceAsStream("/com/groep3/data/images/IconPriceHighLow.png"), 20, 20, true, true);
        //final Image iconPriceLowHigh = new Image(getClass().getResourceAsStream("/com/groep3/data/images/IconPriceLowHigh.png"), 20, 20, true, true);
        ImageView imageView = new ImageView(filterImage);
        ImageView iconAZView = new ImageView(iconAZ);
        //ImageView iconZAView = new ImageView(iconZA);
        //ImageView iconPriceHighLowView = new ImageView(iconPriceHighLow);
        //ImageView iconPriceLowHighView = new ImageView(iconPriceLowHigh);
        filterButton.setGraphic(imageView);
    

        MenuItem menuItemAZ = new MenuItem();
        menuItemAZ.setGraphic(iconAZView);
        MenuItem menuItemZA = new MenuItem("ZA/AZ");
        //menuItemZA.setGraphic(iconZAView);
        MenuItem menuItemPriceHL = new MenuItem("Price High-Low");
        //menuItemPriceHL.setGraphic(iconPriceHighLowView);
        MenuItem menuItemPriceLH = new MenuItem("Price Low-High");
        //menuItemPriceLH.setGraphic(iconPriceLowHighView);
        filterButton.getItems().addAll(menuItemAZ, menuItemZA, menuItemPriceHL, menuItemPriceLH);

        filterButton.setOnAction(e -> openFilterDropdown()); 
        //filterButton.getItems().addAll(new MenuItem("AZ/ZA"), new MenuItem("Price Low-High/High-Low")); 
        
        } 

    private void openFilterDropdown() { 
        
        //MenuButton menuButton = new MenuButton();
        //new MenuItem("test");
        
    }
} 
