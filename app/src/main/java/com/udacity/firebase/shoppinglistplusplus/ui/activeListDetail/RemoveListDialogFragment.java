package com.udacity.firebase.shoppinglistplusplus.ui.activeListDetail;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import com.firebase.client.Firebase;
import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.model.ShoppingList;
import com.udacity.firebase.shoppinglistplusplus.utils.Constants;

/**
 * Created by Jan on 25.01.2017.
 */

public class RemoveListDialogFragment extends DialogFragment {

    private String mPushIDList;
    private String mListName;

    public static RemoveListDialogFragment newInstance(ShoppingList shoppingList, String pushIDList) {
        RemoveListDialogFragment removeListDialogFragment = new RemoveListDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.BUNDLE_KEY_LIST_NAME, shoppingList.getListName());
        bundle.putString(Constants.BUNDLE_KEY_PUSH_ID, pushIDList);
        removeListDialogFragment.setArguments(bundle);
        return removeListDialogFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPushIDList = getArguments().getString(Constants.BUNDLE_KEY_PUSH_ID);
        mListName = getArguments().getString(Constants.BUNDLE_KEY_LIST_NAME);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.CustomTheme_Dialog);
        builder.setTitle("Remove this List");
        builder.setMessage("Really want to remove " + mListName + "?");
        builder.setPositiveButton(getString(R.string.positive_button_remove), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                removeActiveList(mPushIDList);
                getActivity().finish();
            }
        });

        builder.setNegativeButton(getString(R.string.negative_button_cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        return builder.create();
    }

    private void removeActiveList(String pushIDList) {

        // all Nodes where the pushID is used to store data
        Firebase refActiveList = new Firebase(Constants.FIREBASE_URL_ACTIVE_LISTS).child(pushIDList);
        Firebase refShoppingItems = new Firebase(Constants.FIREBASE_URL_SHOPPING_ITEMS).child(pushIDList);

        //Remove all Nodes Associated with the pushID
        refActiveList.removeValue();
        refShoppingItems.removeValue();
    }
}
