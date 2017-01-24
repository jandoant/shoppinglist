package com.udacity.firebase.shoppinglistplusplus.model;

/**
 * Created by Jan on 24.01.2017.
 */

public class ShoppingList {

    private String listName;
    private String owner;

    public ShoppingList() {
    }

    public ShoppingList(String listName, String owner) {
        this.listName = listName;
        this.owner = owner;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
