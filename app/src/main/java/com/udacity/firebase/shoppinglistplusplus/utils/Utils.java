package com.udacity.firebase.shoppinglistplusplus.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Patterns;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility class
 */
public class Utils {
    /**
     * Format the date with SimpleDateFormat
     */
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private Context mContext = null;

    /**
     * Public constructor that takes mContext for later use
     */
    public Utils(Context con) {
        mContext = con;
    }

    public static String formatDate(long dateInMillis) {
        Date date = new Date(dateInMillis);
        return SIMPLE_DATE_FORMAT.format(date);
    }

    /*
    Validation Login-Data
     */

    public static boolean isUserNameValid(String userName) {

        if (TextUtils.isEmpty(userName)) {
            return false;
        } else {
            String[] userNameArr = userName.split(" ");
            return userNameArr.length > 1;
        }
    }

    /**
     * Clientside checks wether an Email is valid or not
     *
     * @param email - EMail to evaluate
     * @return true if EMail is valid, false if not
     */
    public static boolean isEmailValid(String email) {
       /* Email must be entered and match Google's Pattern for valid Email*/
        if (TextUtils.isEmpty(email)) {
            return false;
        } else {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }

    /**
     * Clientside checks wether a Password is valid or not
     * Requirements at this point: Password must be at least 5 characters long
     *
     * @param password
     * @return true if password is valid, false if not
     */
    public static boolean isPasswordValid(String password) {
        /* Password must be at least 5 Characters long*/
        return password.length() >= 5;
    }
}
