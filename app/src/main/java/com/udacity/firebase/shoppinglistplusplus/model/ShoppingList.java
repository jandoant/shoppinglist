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
    private HashMap<String, Object> timestampCreated;

    public ShoppingList() {
    }

    public ShoppingList(String listName, String owner) {
        this.listName = listName;
        this.owner = owner;

        //assign Timestamp created from Firebase Server
        //this only happens once, when the Object is instanciated
        HashMap<String, Object> timestampCreatedObj = new HashMap<String, Object>();
        timestampCreatedObj.put(Constants.FIREBASE_KEY_TIMESTAMP, ServerValue.TIMESTAMP);
        this.timestampCreated = timestampCreatedObj;

        //assign Timestamp last changed from Firebase Server
        HashMap<String, Object> timestampLastChangedObj = new HashMap<String, Object>();
        timestampLastChangedObj.put(Constants.FIREBASE_KEY_TIMESTAMP, ServerValue.TIMESTAMP);
        this.timestampLastChanged = timestampLastChangedObj;
    }

    public HashMap<String, Object> getTimestampCreated() {
        return timestampCreated;
    }

    public HashMap<String, Object> getTimestampLastChanged() {
        return timestampLastChanged;
    }

    @JsonIgnore
    public long getTimestampCreatedInMillis() {
        return (long) this.timestampCreated.get(Constants.FIREBASE_KEY_TIMESTAMP);
    }

    @JsonIgnore
    public long getTimestampLastChangedInMillis() {
        return (long) this.timestampLastChanged.get(Constants.FIREBASE_KEY_TIMESTAMP);
    }

    public String getListName() {
        return listName;
    }

    public String getOwner() {
        return owner;
    }
}
