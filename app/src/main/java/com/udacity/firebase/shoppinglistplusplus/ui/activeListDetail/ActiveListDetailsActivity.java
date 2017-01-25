package com.udacity.firebase.shoppinglistplusplus.ui.activeListDetail;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.model.ShoppingList;
import com.udacity.firebase.shoppinglistplusplus.ui.BaseActivity;
import com.udacity.firebase.shoppinglistplusplus.utils.Constants;

public class ActiveListDetailsActivity extends BaseActivity {

    ShoppingList mShoppingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_list_details);

        invalidateOptionsMenu();

        //SetUp Toolbar
        final Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar_detail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //read from Active List Node
        Firebase ref = new Firebase(Constants.FIREBASE_URL_ACTIVE_LISTS).child("-KbK1y_kHvB9CvvH-9we");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                mShoppingList = dataSnapshot.getValue(ShoppingList.class);

                if (mShoppingList != null) {
                    toolbar.setTitle(mShoppingList.getListName());
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_edit_single_list:
                showEditListNameDialog();
                return true;

            case R.id.action_delete_single_list:
                //Todo: implement delete action
                return true;

            default:
                break;
        }//Ende switch

        return super.onOptionsItemSelected(item);
    }

    private void showEditListNameDialog() {
       /* Create an instance of the dialog fragment and show it */
        DialogFragment dialog = EditListNameDialogFragment.newInstance(mShoppingList);
        dialog.show(this.getFragmentManager(), "EditListNameDialogFragment");
    }
}
