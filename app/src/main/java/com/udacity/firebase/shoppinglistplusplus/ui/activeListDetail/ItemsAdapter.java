package com.udacity.firebase.shoppinglistplusplus.ui.activeListDetail;

import android.app.Activity;
import android.app.DialogFragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.Query;
import com.firebase.ui.FirebaseListAdapter;
import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.model.ShoppingListItem;

/**
 * Created by Jan on 25.01.2017.
 */

public class ItemsAdapter extends FirebaseListAdapter<ShoppingListItem> {

    private String mPushIDList;

    public ItemsAdapter(Activity activity, Query ref, String pushIDList) {
        super(activity, ShoppingListItem.class, R.layout.single_shopping_item, ref);
        mPushIDList = pushIDList;
    }

    @Override
    protected void populateView(View v, ShoppingListItem model, final int position) {
        TextView txt_item_name = (TextView) v.findViewById(R.id.txt_item_name);
        TextView txt_item_owner = (TextView) v.findViewById(R.id.txt_item_owner);
        ImageView btn_remove_item = (ImageView) v.findViewById(R.id.btn_remove_item);

        txt_item_name.setText(model.getName());
        txt_item_owner.setText(model.getOwner());

        btn_remove_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRemoveItemDialog(position);
            }
        });
    }

    private void showRemoveItemDialog(int position) {
        String pushIDItem = getRef(position).getKey();

        /* Create an instance of the dialog fragment and show it */
        /* Create an instance of the dialog fragment and show it */
        DialogFragment dialog = RemoveItemDialogFragment.newInstance(getItem(position), mPushIDList, pushIDItem);
        dialog.show(mActivity.getFragmentManager(), "RemoveItemNameDialogFragment");
    }
}
