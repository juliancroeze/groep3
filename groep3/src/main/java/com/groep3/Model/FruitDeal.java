package com.groep3.model;

public class FruitDeal extends Fruit {
    private int percentage;
    private String salePercentageLabel;

    public FruitDeal(String name, String description, String imagePath, double price) {
        super(name, description, imagePath, price);
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setSalePercentageLabel(String salePercentageLabel) {
        this.salePercentageLabel = "-" + percentage + "%";
    }

    public String getSalePercentageLabel() {
        return salePercentageLabel;
    }

    @Override
    public double getPrice() {
        return price * (100.0 - percentage) / 100.0;
    }

    public double getOriginalPrice() {
        return price;
    }
}