package com.groep3.model;

import java.util.ArrayList;
import java.util.List;

public class FruitBasket extends Product {

    private List<String> containedFruits = new ArrayList<>();

    public FruitBasket(String name, String description, String imagePath, double price) {
        super(name, description, imagePath, price);
    }

    public List<String> getContainedFruits() {
        return containedFruits;
    }

    public void setContainedFruits(List<String> containedFruits) {
        this.containedFruits = containedFruits;
    }
}