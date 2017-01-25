package com.udacity.firebase.shoppinglistplusplus.ui.activeLists;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.Query;
import com.firebase.ui.FirebaseListAdapter;
import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.model.ShoppingList;
import com.udacity.firebase.shoppinglistplusplus.utils.Utils;

/**
 * Created by Jan on 25.01.2017.
 */

public class ActiveListsAdapter extends FirebaseListAdapter<ShoppingList> {

    public ActiveListsAdapter(Activity activity, Query ref) {
        super(activity, ShoppingList.class, R.layout.single_active_list, ref);
    }

    @Override
    protected void populateView(View v, ShoppingList shoppingList, int position) {

        TextView mTextViewListName = (TextView) v.findViewById(R.id.text_view_list_name);
        TextView mTextViewCreatedByUser = (TextView) v.findViewById(R.id.text_view_created_by_user);
        TextView mTextViewEditTime = (TextView) v.findViewById(R.id.text_view_edit_time);

        mTextViewListName.setText(shoppingList.getListName());
        mTextViewCreatedByUser.setText(shoppingList.getOwner());
        mTextViewEditTime.setText(Utils.formatDate(shoppingList.getTimestampLastChangedInMillis()));
    }
}
