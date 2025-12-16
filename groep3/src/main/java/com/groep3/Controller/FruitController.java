package com.groep3.controller;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.groep3.App;
import com.groep3.model.Fruit;
import com.groep3.model.Producent;
import com.groep3.model.Vrucht;
import com.groep3.model.VruchtenList;

public class FruitController {
    private List<Fruit> fruits;

    public FruitController() {
        fruits = new ArrayList<Fruit>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            VruchtenList vl = objectMapper.readValue(App.class.getResourceAsStream("data/vruchtenlijst.json"), VruchtenList.class);
            List<Fruit> mapped = new ArrayList<>();
            for (Vrucht v : vl.getVruchten()) {
                ObjectNode node = objectMapper.createObjectNode();
                node.put("name", v.getNaam());
                String desc = (v.getVerhaal() == null ? "" : v.getVerhaal()) + " (Herkomst: " + v.getHerkomst() + ")";
                node.put("description", desc);
                node.put("imagePath", "data/images/placeholder.png");
                double price = Math.max(0.49, Math.round((100.0 / Math.max(1, v.getBeschikbaarheidNl() / 1000000.0)) * 100.0) / 100.0);
                node.put("price", price);
                Producent p = v.getProducent();
                String boer = (p != null && p.getBoer() != null) ? p.getBoer() : (p != null ? p.getBoerderij() : null);
                node.put("producer", boer);
                Fruit f = objectMapper.treeToValue((JsonNode) node, Fruit.class);
                mapped.add(f);
            }
            fruits = mapped;
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