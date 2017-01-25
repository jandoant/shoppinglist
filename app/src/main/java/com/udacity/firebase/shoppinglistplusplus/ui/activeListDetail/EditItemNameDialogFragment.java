package com.udacity.firebase.shoppinglistplusplus.ui.activeListDetail;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;

import com.firebase.client.Firebase;
import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.model.ShoppingListItem;
import com.udacity.firebase.shoppinglistplusplus.utils.Constants;

import java.util.HashMap;

/**
 * Created by Jan on 25.01.2017.
 */
public class EditItemNameDialogFragment extends EditListDialogFragment {

    private String mPushIdList;
    private String mPushIdItem;
    private String mItemName;

    /**
     * Public static constructor that creates fragment and passes a bundle with data into it when adapter is created
     */
    public static EditItemNameDialogFragment newInstance(ShoppingListItem shoppingListItem, String pushIDList, String pushIDItem) {

        EditItemNameDialogFragment editItemNameDialogFragment = new EditItemNameDialogFragment();

        /*
        * Create Bundle with information on shoppingListName and the LayoutResourceID
        * can be used in onCreate via getArguments();
         */
        Bundle bundle = helpCreateBundle(R.layout.dialog_edit_list);
        bundle.putString(Constants.BUNDLE_KEY_ITEM_NAME, shoppingListItem.getName());
        bundle.putString(Constants.BUNDLE_KEY_PUSH_ID_LIST, pushIDList);
        bundle.putString(Constants.BUNDLE_KEY_PUSH_ID_ITEM, pushIDItem);
        editItemNameDialogFragment.setArguments(bundle);

        return editItemNameDialogFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPushIdList = getArguments().getString(Constants.BUNDLE_KEY_PUSH_ID_LIST);
        mPushIdItem = getArguments().getString(Constants.BUNDLE_KEY_PUSH_ID_ITEM);
        mItemName = getArguments().getString(Constants.BUNDLE_KEY_ITEM_NAME);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.createDialogHelper(R.string.positive_button_edit_item);
        //--set Title
        dialog.setTitle("Edit the name of the Item");
        //--set DefaultValue
        super.helpSetDefaultValueEditText(mItemName);

        return dialog;
    }

    @Override
    protected void doListEdit() {

        final String inputItemName = mEditTextForList.getText().toString().trim();

        if (!TextUtils.isEmpty(inputItemName) && !TextUtils.isEmpty(mPushIdList)) {
            if (!inputItemName.equals(mItemName)) {

                //Do Update Action
                /*
                * update specific Properties in Firebase
                */
                Firebase itemRef = new Firebase(Constants.FIREBASE_URL_SHOPPING_ITEMS).child(mPushIdList).child(mPushIdItem);

                /*
                * Hashmap for the specific properties to be updated
                */
                HashMap<String, Object> updatedProperties = new HashMap<String, Object>();
                //-- List Name
                updatedProperties.put(Constants.FIREBASE_KEY_ITEM_NAME, inputItemName);

                /* Do the update */
                itemRef.updateChildren(updatedProperties);
            }
        }
    }
}

