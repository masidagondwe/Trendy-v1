package com.temwa.tech.trendy;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.temwa.tech.trendy.model.TrendyDeal;
import com.temwa.tech.trendy.util.TrendyDeals;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HomeFragment extends Fragment implements View.OnClickListener{


    private static final String TAG = "HomeFragment";

    //constants

    //widgets
    private RecyclerView mRecyclerView;
    FloatingActionButton btnAddDeal;

    //vars
    private ArrayList<TrendyDeal> mMatches = new ArrayList<>();
    private TrendyDealAdapter mRecyclerViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Log.d(TAG, "onCreateView: started.");
        mRecyclerView = view.findViewById(R.id.recycler_view_deals);
        btnAddDeal = view.findViewById(R.id.add_deal_fab);

        btnAddDeal.setOnClickListener(this);

        Log.d(TAG, "initRecyclerView: after fab click.");
        findMatches();

        return view;
    }


   private void findMatches() {
       TrendyDeals deals = new TrendyDeals();
        if (mMatches != null) {
            mMatches.clear();
        }
        for (TrendyDeal deal : deals.DEALS) {
            mMatches.add(deal);
        }
        if (mRecyclerViewAdapter == null) {
            initRecyclerView();
        }
    }

/*    private void getSavedPreferences(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mSelectedInterest = preferences.getString(PreferenceKeys.INTERESTED_IN, getString(R.string.interested_in_anyone));
        Log.d(TAG, "getSavedPreferences: got selected interest: " + mSelectedInterest);
    }*/

   private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        mRecyclerViewAdapter = new TrendyDealAdapter(getActivity(), mMatches);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Log.d(TAG, "initRecyclerView: init recyclerview end.");
    }

    @Override
    public void onClick(View view) {

        Log.d(TAG, "initRecyclerView: fab clicked");
        AddDealFragment fragment = new AddDealFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.main_content_frame, fragment, getString(R.string.tag_fragment_add_deal));
    }

/*    @Override
    public void onResume() {
        super.onResume();
        FirebaseUtil.openFbReference("trendydeals", this);
        mRecyclerViewAdapter = new TrendyDealAdapter();

        Log.d(TAG, "initRecyclerView: init recyclerview.");
        mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(NUM_COLUMNS, LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mStaggeredGridLayoutManager);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        FirebaseUtil.attachListener();
    }

    @Override
    public void onPause() {
        super.onPause();
        FirebaseUtil.detachListener();
    }*/
}
