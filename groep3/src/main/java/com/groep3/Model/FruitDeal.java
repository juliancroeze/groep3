package com.groep3.model;

public class FruitDeal extends Fruit {
    String salePercentageLabel;
    int pertcentage = 12;
    Double alteredPrice;

    
    public FruitDeal(String name, String description, String imagePath, double price) {
        super(name, description, imagePath, price);
    };

    @Override
    public double getPrice() {
        int maxPercentage = 100;

        int calculatedPercentage = maxPercentage - pertcentage;

        return price / maxPercentage * calculatedPercentage;

    };

}
