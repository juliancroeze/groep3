package com.groep3.Model;

public class Fruit {
    public String name;
    public String description;
    public String imagePath;

    //Voor fruit met image
    public Fruit(String name, String description, String imagePath) {
        this.name = name;
        this.description = description;
        this.imagePath = imagePath;
    }

    //Voor fruit zonder image
    public Fruit(String name, String description) {
        this.name = name;
        this.description = description;
        this.imagePath = null;
    }

    //Getters and Setters
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

}
