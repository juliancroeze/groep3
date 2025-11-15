package com.groep3.Controller;

import java.util.List;
import java.util.ArrayList;
import com.groep3.Model.Fruit;

public class ShoppingCartController {
    public List<Fruit> cartItems;

    public ShoppingCartController() {
        cartItems = new ArrayList<>();
    }

    public void add(Fruit fruit) {
        cartItems.add(fruit);
    }

    public void remove(Fruit fruit) {
        cartItems.remove(fruit);
    }

    public List<Fruit> getItems() {
        return cartItems;
    }
}
