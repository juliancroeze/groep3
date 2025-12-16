package com.groep3.model;

public class Fruit {
    private String name;
    private String description;
    private String imagePath;
    private double price;


    private String producer;

    public Fruit() {
        
    }

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

    // Getters en setters

    public String getProducer() { return producer; }
    public void setProducer(String producer) { this.producer = producer; }

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