package com.groep3.Controller;

import java.util.List;

import com.groep3.Model.Fruit;

public class FruitController {
    private List<Fruit> fruits;

    public FruitController() {
        fruits = List.of(
            new Fruit("Apple", "A sweet red fruit", "/images/apple.png"),
            new Fruit("Banana", "A long yellow fruit", "/images/banana.png"),
            new Fruit("Orange", "A round citrus fruit", "/images/orange.png"),
            new Fruit("Orange", "A round citrus fruit", "/images/orange.png")
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
