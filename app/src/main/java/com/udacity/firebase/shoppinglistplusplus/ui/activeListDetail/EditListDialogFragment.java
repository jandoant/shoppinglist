package com.udacity.firebase.shoppinglistplusplus.ui.activeListDetail;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.utils.Constants;

/**
 * Created by Jan on 25.01.2017.
 */

public abstract class EditListDialogFragment extends DialogFragment {

    EditText mEditTextForList;
    int mResource;

    /**
     * Helper method that creates a basic bundle of all of the information needed to change
     * values in a shopping list.
     *
     * @param layoutResource
     * @return
     */
    protected static Bundle helpCreateBundle(int layoutResource) {
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.BUNDLE_KEY_LAYOUT_RESOURCE, layoutResource);
        return bundle;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mResource = getArguments().getInt(Constants.BUNDLE_KEY_LAYOUT_RESOURCE);
    }

    /**
     * Open the keyboard automatically when the dialog fragment is opened
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    protected Dialog createDialogHelper(int stringResourceForPositiveButton) {
        /*
        * Set Up the Builder and the Theme of the Dialog
        */
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.CustomTheme_Dialog);
        /*
        * Prepare the Layout for the Dialog
        */
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View rootView = inflater.inflate(mResource, null);
        mEditTextForList = (EditText) rootView.findViewById(R.id.edit_text_update_list_name);

        /**
         * Call doListEdit() when user taps "Done" keyboard action
         */
        mEditTextForList.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE || keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    doListEdit();

                    /**
                     * Close the dialog fragment when done
                     */
                    EditListDialogFragment.this.getDialog().cancel();
                }
                return true;
            }
        });

        /*
        * Inflate and set the layout for the dialog
        * Pass null as the parent view because its going in the dialog layout
        */
        builder.setView(rootView);

        /*
        * Define the positive Button
        */
        builder.setPositiveButton(stringResourceForPositiveButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                doListEdit();

                /**
                 * Close the dialog fragment
                 */
                EditListDialogFragment.this.getDialog().cancel();
            }
        });

        /*
        * Define the negative Button
        */

        builder.setNegativeButton(getString(R.string.negative_button_cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                /**
                 * Close the dialog fragment
                 */
                EditListDialogFragment.this.getDialog().cancel();
            }
        });

        return builder.create();
    }

    /**
     * abstract Method to implement Editing Features (Edit, Delete) on List
     */
    protected abstract void doListEdit();

    /**
     * Set the EditText text to be the inputted text
     * and put the pointer at the end of the input
     *
     * @param defaultText
     */
    protected void helpSetDefaultValueEditText(String defaultText) {
        mEditTextForList.setText(defaultText);
        mEditTextForList.setSelection(defaultText.length());
    }
}
