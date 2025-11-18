package com.groep3.Controller;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.groep3.App;
import com.groep3.Model.Fruit;

public class FruitController {
    private List<Fruit> fruits;

    public FruitController() {

        fruits = new ArrayList<Fruit>();
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            fruits = List.of(
                objectMapper.readValue(App.class.getResourceAsStream("data/fruits.json"), Fruit[].class)
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
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
