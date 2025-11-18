package com.groep3.Controller;

import java.util.HashMap;

import com.groep3.Model.Fruit;

public class ShoppingCartController {
    // public List<Fruit> cartItems;
    public HashMap<Fruit, Integer> cartItems;

    public ShoppingCartController() {
        cartItems = new HashMap<Fruit, Integer>();
    }

    public void add(Fruit fruit) {
        cartItems.put(fruit, cartItems.getOrDefault(fruit, 0) + 1);
    }

    public void remove(Fruit fruit) {
        cartItems.remove(fruit);
    }

    public HashMap<Fruit, Integer> getItems() {
        return cartItems;
    }
}
