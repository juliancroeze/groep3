package com.groep3.model;

public class FruitDeal extends Fruit {
    private int percentage;
    private String salePercentageLabel;

    public FruitDeal(String name, String description, String imagePath, double price) {
        super(name, description, imagePath, price);
    }

    public void setPercentage(int percentage) {
        this.percentage = Math.max(0, Math.min(100, percentage));
        this.salePercentageLabel = "-" + this.percentage + "%";
    }

    public int getPercentage() {
        return percentage;
    }

    public String getSalePercentageLabel() {
        return salePercentageLabel;
    }

    @Override
    public double getPrice() {
        return super.getPrice() * (100.0 - percentage) / 100.0;
    }

    public double getOriginalPrice() {
        return super.getPrice();
    }
}