package com.groep3.model;

public class OrganicFruitBasket extends FruitBasket {

    private double organicPremium;

    public OrganicFruitBasket(String name, String description, double price, String imagePath, double organicPremium) {
        super(name, description, imagePath, price);
        this.organicPremium = organicPremium;
    }

    @Override
    public double getPrice() {
        return super.getPrice() + organicPremium;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " (100% biologisch)";
    }

    public double getOriginalPrice() {
        return super.getPrice();
    }
}