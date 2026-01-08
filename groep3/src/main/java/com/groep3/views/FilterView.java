package com.groep3.views;

import javafx.fxml.FXML; 
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView; 
import com.groep3.controller.FilterController; 


public class FilterView {
    private FilterController filterController;

    @FXML private MenuButton filterButton; 
    @FXML private MenuItem menuItemAZ;
    @FXML private MenuItem menuItemZA;
    @FXML private MenuItem menuItemPriceLH;
    @FXML private MenuItem menuItemPriceHL;

    @FXML
    private void initialize() { 
        final Image filterImage = new Image(getClass().getResourceAsStream("/com/groep3/data/images/FilterIcon.png"), 20, 20, true, true);
        final Image iconAZ = new Image(getClass().getResourceAsStream("/com/groep3/data/images/IconAZ.png"), 20, 20, true, true);
        final Image iconZA = new Image(getClass().getResourceAsStream("/com/groep3/data/images/IconZA.png"), 20, 20, true, true);
        final Image iconPriceHighLow = new Image(getClass().getResourceAsStream("/com/groep3/data/images/IconPriceHighLow.png"), 20, 20, true, true);
        final Image iconPriceLowHigh = new Image(getClass().getResourceAsStream("/com/groep3/data/images/IconPriceLowHigh.png"), 20, 20, true, true);
        ImageView imageView = new ImageView(filterImage);
        ImageView iconAZView = new ImageView(iconAZ);
        ImageView iconZAView = new ImageView(iconZA);
        ImageView iconPriceHighLowView = new ImageView(iconPriceHighLow);
        ImageView iconPriceLowHighView = new ImageView(iconPriceLowHigh);
        filterButton.setGraphic(imageView);

        //make icons for menu items
        menuItemAZ.setGraphic(iconAZView);
        menuItemZA.setGraphic(iconZAView);
        menuItemPriceHL.setGraphic(iconPriceHighLowView);
        menuItemPriceLH.setGraphic(iconPriceLowHighView);

    }

    @FXML
    private void filterAZ() {
         com.groep3.App.filterController.applyFilterAZ();
    }

    @FXML
    private void filterZA() {
        com.groep3.App.filterController.applyFilterZA();
    }

    @FXML
    private void filterPriceLowHigh() {
        com.groep3.App.filterController.applyFilterPriceLowHigh();
    }

    @FXML
    private void filterPriceHighLow() {
        com.groep3.App.filterController.applyFilterPriceHighLow();
    }
    
} 
