package com.groep3.Controller;

import java.util.HashMap;

import com.groep3.Model.Fruit;

public class ShoppingCartController {
    // public List<Fruit> cartItems;
    private HashMap<Fruit, Integer> cartItems;

    public ShoppingCartController() {
        cartItems = new HashMap<Fruit, Integer>();
    }

    public HashMap<Fruit, Integer> getCartItems() {
        return cartItems;
    }

    public void add(Fruit fruit) {
        cartItems.put(fruit, cartItems.getOrDefault(fruit, 0) + 1);
    }

    public void remove(Fruit fruit) {
        if (cartItems.containsKey(fruit)) {
            int count = cartItems.get(fruit);
            if (count > 1) {
                cartItems.put(fruit, count - 1); // Count - 1
            } else {
                cartItems.remove(fruit); // Verwijderen wanner count 1 is.
            }
        }
    }
    public HashMap<Fruit, Integer> getItems() {
        return cartItems;
    }
}
