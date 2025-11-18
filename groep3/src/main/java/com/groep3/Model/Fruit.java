package com.groep3.Model;

public class Fruit {
    private String name;
    private String description;
    private String imagePath;
    private double price;

    // Constructor met afbeelding
    public Fruit(String name, String description, String imagePath, double price) {
        this.name = name;
        this.description = description;
        this.imagePath = imagePath;
        this.price = price;
    }

    // Constructor zonder afbeelding
    public Fruit(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.imagePath = null;
        this.price = price;
    }

    // Getters en setters (deze moeten wél public zijn!)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}