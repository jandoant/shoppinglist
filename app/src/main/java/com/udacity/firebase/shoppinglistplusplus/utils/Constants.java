package com.udacity.firebase.shoppinglistplusplus.utils;

import com.udacity.firebase.shoppinglistplusplus.BuildConfig;

/**
 * Constants class store most important strings and paths of the app
 */
public final class Constants {

    /**
     * Constants related to locations in Firebase, such as the name of the node
     * where active lists are stored (ie "activeLists")
     */

    /**
     * Constants for Firebase object properties
     */

    /**
     * Constants for Firebase URL
     */
    public static final String FIREBASE_URL = BuildConfig.UNIQUE_FIREBASE_ROOT_URL;

    //Keys
    public static final String FIREBASE_KEY_TIMESTAMP = "timestamp";
    public static final String FIREBASE_KEY_LIST_NAME = "listName";
    public static final String FIREBASE_KEY_ITEM_NAME = "name";

    //Nodes
    public static final String FIREBASE_NODE_ACTIVE_LIST = "activeLists";
    public static final String FIREBASE_NODE_TIMESTAMP_CREATED = "timestampCreated";
    public static final String FIREBASE_NODE_TIMESTAMP_LAST_CHANGED = "timestampLastChanged";

    public static final String FIREBASE_NODE_SHOPPING_ITEMS = "shoppingItems";

    public static final String FIREBASE_NODE_USERS = "users";
    public static final String FIREBASE_NODE_TIMESTAMP_JOINED = "timestampJoined";
    //URL to specific node
    public static final String FIREBASE_URL_ACTIVE_LISTS = FIREBASE_URL + "/" + FIREBASE_NODE_ACTIVE_LIST;
    public static final String FIREBASE_URL_SHOPPING_ITEMS = FIREBASE_URL + "/" + FIREBASE_NODE_SHOPPING_ITEMS;
    public static final String FIREBASE_URL_USERS = FIREBASE_URL + "/" + FIREBASE_NODE_USERS;
    /**
     * Constants for bundles, extras and shared preferences keys
     */
    public static final String EXTRA_KEY_PUSH_ID = "PUSH_ID";
    public static final String BUNDLE_KEY_LIST_NAME = "LIST_NAME";
    public static final String BUNDLE_KEY_LAYOUT_RESOURCE = "LAYOUT_RESOURCE";
    public static final String BUNDLE_KEY_PUSH_ID_LIST = "PUSH_ID";
    public static final String BUNDLE_KEY_ITEM_NAME = "ITEM_NAME";
    public static final String BUNDLE_KEY_PUSH_ID_ITEM = "PUSH_ID_ITEM";
}
