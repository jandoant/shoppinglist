package com.udacity.firebase.shoppinglistplusplus.ui.activeListDetail;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;

import com.firebase.client.Firebase;
import com.firebase.client.ServerValue;
import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.model.ShoppingList;
import com.udacity.firebase.shoppinglistplusplus.utils.Constants;

import java.util.HashMap;

/**
 * Created by Jan on 25.01.2017.
 */

public class EditListNameDialogFragment extends EditListDialogFragment {

    String mListName;

    /**
     * Public static constructor that creates fragment and passes a bundle with data into it when adapter is created
     */
    public static EditListNameDialogFragment newInstance(ShoppingList shoppingList) {

        EditListNameDialogFragment editListNameDialogFragment = new EditListNameDialogFragment();

        /*
        * Create Bundle with information on shoppingListName and the LayoutResourceID
        * can be used in onCreate via getArguments();
         */
        Bundle bundle = helpCreateBundle(R.layout.dialog_edit_list);
        bundle.putString(Constants.BUNDLE_KEY_LIST_NAME, shoppingList.getListName());
        editListNameDialogFragment.setArguments(bundle);

        return editListNameDialogFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListName = getArguments().getString(Constants.BUNDLE_KEY_LIST_NAME);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.createDialogHelper(R.string.positive_button_update);
        //--set Title
        dialog.setTitle("Edit the name of the List");
        //--set DefaultValue
        super.helpSetDefaultValueEditText(mListName);
        return dialog;
    }

    @Override
    protected void doListEdit() {

        final String inputListName = mEditTextForList.getText().toString().trim();

        if (!TextUtils.isEmpty(inputListName)) {
            if (!inputListName.equals(mListName)) {
                /*
                * update specific Properties in Firebase
                */
                Firebase activeListRef = new Firebase(Constants.FIREBASE_URL_ACTIVE_LIST).child("-KbK1y_kHvB9CvvH-9we");

                /*
                * Hashmap for the specific properties to be updated
                */
                HashMap<String, Object> updatedProperties = new HashMap<String, Object>();
                //-- List Name
                updatedProperties.put(Constants.FIREBASE_KEY_LIST_NAME, inputListName);
                //--Timestamp Last Changed - is a Hashmap iself
                HashMap<String, Object> changedTimestampMap = new HashMap<>();
                changedTimestampMap.put(Constants.FIREBASE_KEY_TIMESTAMP, ServerValue.TIMESTAMP);

                 /* Add the updated timestamp to the Properties-Map */
                updatedProperties.put(Constants.FIREBASE_NODE_TIMESTAMP_LAST_CHANGED, changedTimestampMap);

                /* Do the update */
                activeListRef.updateChildren(updatedProperties);
            }
        }
    }
}
