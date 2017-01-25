package com.udacity.firebase.shoppinglistplusplus.ui.activeListDetail;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.model.ShoppingList;
import com.udacity.firebase.shoppinglistplusplus.model.ShoppingListItem;
import com.udacity.firebase.shoppinglistplusplus.ui.BaseActivity;
import com.udacity.firebase.shoppinglistplusplus.utils.Constants;

public class ActiveListDetailsActivity extends BaseActivity {

    ShoppingList mShoppingList;
    String mPushIDList;
    ItemsAdapter mAdapter;
    private ValueEventListener mActiveListRefListener;
    private Firebase mRefActiveList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_list_details);

        //Receive PushId of the chosen ShoppingList via Intent
        Bundle extras = getIntent().getExtras();
        mPushIDList = extras.getString(Constants.EXTRA_KEY_PUSH_ID);

        invalidateOptionsMenu();

        //SetUp Toolbar
        final Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar_detail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddItemDialog();
            }
        });

        ListView listView = (ListView) findViewById(R.id.list_view_shopping_list_items);
        Firebase refItemList = new Firebase(Constants.FIREBASE_URL_SHOPPING_ITEMS).child(mPushIDList);

        mAdapter = new ItemsAdapter(this, refItemList);
        listView.setAdapter(mAdapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                //extract pushIDList from the clicked Item
                ShoppingListItem shoppingListItem = mAdapter.getItem(position);
                String pushIDItem = mAdapter.getRef(position).getKey();

                showEditItemNameDialog(shoppingListItem, pushIDItem);
                return true;
            }
        });

        //read from Active List Node - needed to dynamically Display the Toolbar Title
        mRefActiveList = new Firebase(Constants.FIREBASE_URL_ACTIVE_LISTS).child(mPushIDList);
        mActiveListRefListener = mRefActiveList.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //extract ShoppingList Object from Firebase
                mShoppingList = dataSnapshot.getValue(ShoppingList.class);

                //set Toolbar Title dynamically
                if (mShoppingList != null) {
                    toolbar.setTitle(mShoppingList.getListName());
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void showAddItemDialog() {
         /* Create an instance of the dialog fragment and show it */
        DialogFragment dialog = AddItemDialogFragment.newInstance(mShoppingList, mPushIDList);
        dialog.show(this.getFragmentManager(), "AddItemDialogFragment");
    }

    private void showEditItemNameDialog(ShoppingListItem shoppingListItem, String pushIDItem) {

        /* Create an instance of the dialog fragment and show it */
        DialogFragment dialog = EditItemNameDialogFragment.newInstance(shoppingListItem, mPushIDList, pushIDItem);
        dialog.show(this.getFragmentManager(), "EditItemNameDialogFragment");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //remove Event Listener
        mRefActiveList.removeEventListener(mActiveListRefListener);
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

            case R.id.action_remove_single_list:
                showRemoveListDialog();
                return true;

            default:
                break;
        }//Ende switch

        return super.onOptionsItemSelected(item);
    }

    private void showEditListNameDialog() {
       /* Create an instance of the dialog fragment and show it */
        DialogFragment dialog = EditListNameDialogFragment.newInstance(mShoppingList, mPushIDList);
        dialog.show(this.getFragmentManager(), "EditListNameDialogFragment");
    }

    private void showRemoveListDialog() {
       /* Create an instance of the dialog fragment and show it */
        DialogFragment dialog = RemoveListDialogFragment.newInstance(mShoppingList, mPushIDList);
        dialog.show(this.getFragmentManager(), "RemoveListDialogFragment");
    }
}
