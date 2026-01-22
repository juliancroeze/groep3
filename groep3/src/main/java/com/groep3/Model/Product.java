package com.groep3.model;

public abstract class Product {

    private String name;
    private String description;
    private String imagePath;
    protected double price;

    public Product(String name, String description, String imagePath, double price) {
        this.name = name;
        this.description = description;
        this.imagePath = imagePath;
        this.price = price;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Product))
            return false;
        Product p = (Product) o;
        return name != null && name.equalsIgnoreCase(p.name);
    }

    @Override
    public int hashCode() {
        return name == null ? 0 : name.toLowerCase().hashCode();
    }
}