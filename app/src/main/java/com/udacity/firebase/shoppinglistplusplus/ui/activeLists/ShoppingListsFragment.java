package com.udacity.firebase.shoppinglistplusplus.ui.activeLists;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.firebase.client.Firebase;
import com.udacity.firebase.shoppinglistplusplus.R;
import com.udacity.firebase.shoppinglistplusplus.ui.activeListDetail.ActiveListDetailsActivity;
import com.udacity.firebase.shoppinglistplusplus.utils.Constants;

/**
 * A simple {@link Fragment} subclass that shows a list of all shopping lists a user can see.
 * Use the {@link ShoppingListsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShoppingListsFragment extends Fragment {
    private ListView mListView;
    private ActiveListsAdapter mAdapter;

    public ShoppingListsFragment() {
        /* Required empty public constructor */
    }

    /**
     * Create fragment and pass bundle with data as it's arguments
     * Right now there are not arguments...but eventually there will be.
     */
    public static ShoppingListsFragment newInstance() {
        ShoppingListsFragment fragment = new ShoppingListsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * Initialize instance variables with data from bundle
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /**
         * Initalize UI elements
         */
        View rootView = inflater.inflate(R.layout.fragment_shopping_lists, container, false);
        initializeScreen(rootView);

        /*
        * Call FirebaseDB-ChildNode and notify app when data under this node has changed
         */
        Firebase activeListRef = new Firebase(Constants.FIREBASE_URL_ACTIVE_LISTS);

        mAdapter = new ActiveListsAdapter(getActivity(), activeListRef);
        mListView.setAdapter(mAdapter);
        /**
         * When User clicks on Item - Detail Screen of that Item gets opened
         */
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //extract pushIDList from the clicked Item
                String pushIDList = mAdapter.getRef(position).getKey();
                //open DetailActivity of the Item that has this pushIDList
                openActiveListDetailsActivity(pushIDList);
            }
        });

        return rootView;
    }

    /**
     * Link layout elements from XML
     */
    private void initializeScreen(View rootView) {
        mListView = (ListView) rootView.findViewById(R.id.list_view_active_lists);
    }

    private void openActiveListDetailsActivity(String pushID) {
        Intent intent = new Intent(getActivity(), ActiveListDetailsActivity.class);
        intent.putExtra(Constants.EXTRA_KEY_PUSH_ID, pushID);
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mAdapter.cleanup();
    }
}
