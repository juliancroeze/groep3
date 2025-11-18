package com.groep3.Controller;

import java.util.List;

import com.groep3.Model.Fruit;

public class FruitController {
    private List<Fruit> fruits;

    public FruitController() {
        fruits = List.of(
            new Fruit("Apple", "A sweet red fruit", "/images/apple.png", 0.79),
            new Fruit("Banana", "Lange gele banaan", "/images/banana.png", 0.29),
            new Fruit("Orange", "Heerlijke sinaasappel", "/images/orange.png", 0.99),
            new Fruit("Kiwi", "Groene kiwi met pitjes", "/images/kiwi.png", 1.49),
            new Fruit("Apple", "A sweet red fruit", "/images/apple.png", 0.79),
            new Fruit("Banana", "Lange gele banaan", "/images/banana.png", 0.29),
            new Fruit("Orange", "Heerlijke sinaasappel", "/images/orange.png", 0.99),
            new Fruit("Kiwi", "Groene kiwi met pitjes", "/images/kiwi.png", 1.49),
            new Fruit("Apple", "A sweet red fruit", "/images/apple.png", 0.79),
            new Fruit("Banana", "Lange gele banaan", "/images/banana.png", 0.29),
            new Fruit("Orange", "Heerlijke sinaasappel", "/images/orange.png", 0.99),
            new Fruit("Kiwi", "Groene kiwi met pitjes", "/images/kiwi.png", 1.49)
        );
    }

    public List<Fruit> getFruits() {
        return fruits;
    }

    public Fruit getFruitByName(String name) {
        for (Fruit fruit : fruits) {
            if (fruit.getName().equalsIgnoreCase(name)) {
                return fruit;
            }
        }
        return null;
    }
}
