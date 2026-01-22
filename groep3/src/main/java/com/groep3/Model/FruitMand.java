package com.groep3.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class FruitMand {

    private String name;
    private String description;
    private String imagePath;
    private double price;
    private List<String> fruits;
    private String type;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public double getPrice() {
        return price;
    }

    @JsonProperty("fruits")
    public List<String> getFruits() {
        return fruits;
    }

    public String getType() {
        return type;
    }
}