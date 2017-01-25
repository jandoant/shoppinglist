package com.udacity.firebase.shoppinglistplusplus.ui.activeListDetail;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.Query;
import com.firebase.ui.FirebaseListAdapter;
import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.model.ShoppingListItem;

/**
 * Created by Jan on 25.01.2017.
 */

public class ItemsAdapter extends FirebaseListAdapter<ShoppingListItem> {
    public ItemsAdapter(Activity activity, Query ref) {
        super(activity, ShoppingListItem.class, R.layout.single_shopping_item, ref);
    }

    @Override
    protected void populateView(View v, ShoppingListItem model, int position) {
        TextView txt_item_name = (TextView) v.findViewById(R.id.txt_item_name);
        TextView txt_item_owner = (TextView) v.findViewById(R.id.txt_item_owner);

        txt_item_name.setText(model.getName());
        txt_item_owner.setText(model.getOwner());
    }
}
