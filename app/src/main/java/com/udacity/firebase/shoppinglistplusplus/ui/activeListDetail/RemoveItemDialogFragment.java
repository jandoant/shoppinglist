package com.udacity.firebase.shoppinglistplusplus.ui.activeListDetail;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import com.firebase.client.Firebase;
import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.model.ShoppingListItem;
import com.udacity.firebase.shoppinglistplusplus.utils.Constants;

/**
 * Created by Jan on 25.01.2017.
 */

public class RemoveItemDialogFragment extends DialogFragment {

    private String mPushIDItem;
    private String mItemName;
    private String mPushIDList;

    public static RemoveItemDialogFragment newInstance(ShoppingListItem shoppingListItem, String pushIDList, String pushIDItem) {
        RemoveItemDialogFragment removeItemDialogFragment = new RemoveItemDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.BUNDLE_KEY_ITEM_NAME, shoppingListItem.getName());
        bundle.putString(Constants.BUNDLE_KEY_PUSH_ID_LIST, pushIDList);
        bundle.putString(Constants.BUNDLE_KEY_PUSH_ID_ITEM, pushIDItem);
        removeItemDialogFragment.setArguments(bundle);
        return removeItemDialogFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPushIDList = getArguments().getString(Constants.BUNDLE_KEY_PUSH_ID_LIST);
        mPushIDItem = getArguments().getString(Constants.BUNDLE_KEY_PUSH_ID_ITEM);
        mItemName = getArguments().getString(Constants.BUNDLE_KEY_ITEM_NAME);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.CustomTheme_Dialog);
        builder.setTitle("Removing this Item");
        builder.setMessage("Really want to remove " + mItemName + "?");
        builder.setPositiveButton(getString(R.string.positive_button_remove), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                removeItem(mPushIDItem);
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

    private void removeItem(String mPushIDItem) {

        Firebase refChosemShoppingItems = new Firebase(Constants.FIREBASE_URL_SHOPPING_ITEMS).child(mPushIDList).child(mPushIDItem);
        refChosemShoppingItems.removeValue();
    }
}
