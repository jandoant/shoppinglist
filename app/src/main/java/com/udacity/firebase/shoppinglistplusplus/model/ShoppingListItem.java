package com.udacity.firebase.shoppinglistplusplus.model;

/**
 * Created by Jan on 25.01.2017.
 */

public class ShoppingListItem {

    String name;
    String owner;

    public ShoppingListItem() {
    }

    public ShoppingListItem(String name, String owner) {
        this.name = name;
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }
}
