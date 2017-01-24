package com.udacity.firebase.shoppinglistplusplus.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.model.ShoppingList;
import com.udacity.firebase.shoppinglistplusplus.utils.Constants;

public class ActiveListDetailsActivity extends BaseActivity {

    ShoppingList mShoppingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_list_details);

        //SetUp Toolbar
        final Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar_detail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //read from Active List Node
        Firebase ref = new Firebase(Constants.FIREBASE_URL).child(Constants.FIREBASE_NODE_ACTIVE_LIST);

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
}
