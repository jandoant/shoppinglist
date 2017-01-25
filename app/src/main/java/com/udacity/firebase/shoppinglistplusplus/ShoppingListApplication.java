package com.udacity.firebase.shoppinglistplusplus;

import com.firebase.client.Firebase;
import com.firebase.client.Logger;

/**
 * Includes one-time initialization of Firebase related code
 */
public class ShoppingListApplication extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();

        /* Initialize Firebase */
        Firebase.setAndroidContext(this);
        /*Receive Debug Information in Console*/
        Firebase.getDefaultConfig().setLogLevel(Logger.Level.DEBUG);
    }
}