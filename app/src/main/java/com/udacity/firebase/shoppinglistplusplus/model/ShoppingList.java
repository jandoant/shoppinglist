package com.udacity.firebase.shoppinglistplusplus.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.firebase.client.ServerValue;
import com.udacity.firebase.shoppinglistplusplus.utils.Constants;

import java.util.HashMap;

/**
 * Created by Jan on 24.01.2017.
 */

public class ShoppingList {

    private String listName;
    private String owner;
    private HashMap<String, Object> timestampLastChanged;

    public ShoppingList() {
    }

    public ShoppingList(String listName, String owner) {
        this.listName = listName;
        this.owner = owner;

        //assign timestamp from Firebase Server
        HashMap<String, Object> timestampLastChangedObj = new HashMap<String, Object>();
        timestampLastChangedObj.put(Constants.FIREBASE_PROPERTY_TIMESTAMP, ServerValue.TIMESTAMP);
        this.timestampLastChanged = timestampLastChangedObj;
    }

    public HashMap<String, Object> getTimestampLastChanged() {
        return timestampLastChanged;
    }

    @JsonIgnore
    public long getTimestampLastChangedInMillis() {
        return (long) this.timestampLastChanged.get(Constants.FIREBASE_PROPERTY_TIMESTAMP);
    }

    public String getListName() {
        return listName;
    }

    public String getOwner() {
        return owner;
    }
}
