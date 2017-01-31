package com.udacity.firebase.shoppinglistplusplus.model;

import com.firebase.client.ServerValue;
import com.udacity.firebase.shoppinglistplusplus.utils.Constants;

import java.util.HashMap;

/**
 * Created by Jan on 31.01.2017.
 */

public class User {

    String name;
    String email;
    private HashMap<String, Object> timestampJoined;

    public User() {
    }

    public User(String email, String name) {
        this.email = email;
        this.name = name;

        //assign Timestamp joined from Firebase Server
        //this only happens once, when the Object is instanciated
        HashMap<String, Object> timestampJoinedObj = new HashMap<String, Object>();
        timestampJoinedObj.put(Constants.FIREBASE_KEY_TIMESTAMP, ServerValue.TIMESTAMP);
        this.timestampJoined = timestampJoinedObj;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public HashMap<String, Object> getTimestampJoined() {
        return timestampJoined;
    }
}
