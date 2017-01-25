package com.udacity.firebase.shoppinglistplusplus.ui.activeListDetail;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.firebase.client.Firebase;
import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.model.ShoppingList;
import com.udacity.firebase.shoppinglistplusplus.model.ShoppingListItem;
import com.udacity.firebase.shoppinglistplusplus.utils.Constants;

/**
 * Created by Jan on 25.01.2017.
 */

public class AddItemDialogFragment extends EditListDialogFragment {

    String mListName;
    String mPushIDList;

    /**
     * Public static constructor that creates fragment and passes a bundle with data into it when adapter is created
     */
    public static AddItemDialogFragment newInstance(ShoppingList shoppingList, String pushID) {

        AddItemDialogFragment addItemDialogFragment = new AddItemDialogFragment();

        /*
        * Create Bundle with information on shoppingListName and the LayoutResourceID
        * can be used in onCreate via getArguments();
         */
        Bundle bundle = helpCreateBundle(R.layout.dialog_edit_list);
        bundle.putString(Constants.BUNDLE_KEY_LIST_NAME, shoppingList.getListName());
        bundle.putString(Constants.BUNDLE_KEY_PUSH_ID_LIST, pushID);
        addItemDialogFragment.setArguments(bundle);

        return addItemDialogFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mListName = getArguments().getString(Constants.BUNDLE_KEY_LIST_NAME);
        mPushIDList = getArguments().getString(Constants.BUNDLE_KEY_PUSH_ID_LIST);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.createDialogHelper(R.string.positive_button_add_list_item);
        //--set Title
        dialog.setTitle("Add a List Item");
        return dialog;
    }

    @Override
    protected void doListEdit() {
        Log.d("TAG1", "Created new Shopping Item in " + mPushIDList);
        String itemName = mEditTextForList.getText().toString().trim();

        if (!TextUtils.isEmpty(itemName)) {
            ShoppingListItem shoppingListItem = new ShoppingListItem(itemName, "Dummy Item User");
            Firebase refShoppingItems = new Firebase(Constants.FIREBASE_URL_SHOPPING_ITEMS);
            refShoppingItems.child(mPushIDList).push().setValue(shoppingListItem);
        }
    }
}
