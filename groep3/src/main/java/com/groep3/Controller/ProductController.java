package com.groep3.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.groep3.App;
import com.groep3.model.*;
import java.util.ArrayList;
import java.util.List;

public class ProductController {

    private List<Product> products = new ArrayList<>();

    public ProductController() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            VruchtenList vl = mapper.readValue(App.class.getResourceAsStream("data/vruchtenlijst.json"), VruchtenList.class);

            for (Vrucht v : vl.getVruchten()) {
                String desc = v.getVerhaal() != null ? v.getVerhaal() : "";
                String imagePath = "data/images/placeholder.png";

                double basePrice = Math.max(0.49,
                        Math.round((100.0 / Math.max(1, v.getBeschikbaarheidNl() / 1000000.0)) * 100.0) / 100.0);

                Fruit fruit;
                if (v.getPercentage() != null && v.getPercentage() > 0) {
                    FruitDeal deal = new FruitDeal(v.getNaam(), desc, imagePath, basePrice);
                    deal.setPercentage(v.getPercentage());
                    fruit = deal;
                } else {
                    fruit = new Fruit(v.getNaam(), desc, imagePath, basePrice);
                }

                fruit.setCategorie(v.getCategorie());
                fruit.setHerkomst(v.getHerkomst());
                fruit.setSoort(v.getSoortFruit());
                fruit.setBeschikbaarheid(v.getBeschikbaarheidNl());

                Producent p = v.getProducent();
                if (p != null) {
                    fruit.setBoerderij(p.getBoerderij());
                    fruit.setStreek(p.getProvincieOfStreek());
                    fruit.setBoerBericht(p.getBoerBericht());
                    fruit.setProducer(p.getBoer() != null ? p.getBoer() : p.getBoerderij());
                }

                products.add(fruit);
            }

            FruitMandenLijst fml = mapper.readValue(App.class.getResourceAsStream("data/fruitmanden.json"), FruitMandenLijst.class);

            for (FruitMand mand : fml.getFruitmanden()) {
                double premium = mand.getPrice() * 0.20;
                String imagePath = "data/images/fruitmand.png";


                FruitBasket basket;
                if ("organic".equalsIgnoreCase(mand.getType())) {
                    basket = new OrganicFruitBasket(mand.getName(), mand.getDescription(),
                            mand.getPrice() - premium, imagePath, premium);
                } else {
                    basket = new FruitBasket(mand.getName(), mand.getDescription(),
                    imagePath, mand.getPrice());
                }

                basket.setContainedFruits(mand.getFruits());
                products.add(basket);
            }

            products.sort((p1, p2) -> {
                int priority1 = getPriority(p1);
                int priority2 = getPriority(p2);

                if (priority1 != priority2) {
                    return Integer.compare(priority1, priority2);
                }

                return p1.getName().compareToIgnoreCase(p2.getName());
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int getPriority(Product p) {
        if (p instanceof FruitBasket) {
            return 0; 
        }
        if (p instanceof FruitDeal) {
            return 1;
        }
        return 2;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Product getProductByName(String name) {
        return products.stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}