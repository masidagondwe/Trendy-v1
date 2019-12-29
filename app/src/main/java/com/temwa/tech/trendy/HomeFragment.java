package com.temwa.tech.trendy;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.temwa.tech.trendy.model.TrendyDeal;
import com.temwa.tech.trendy.util.FirebaseUtil;
import com.temwa.tech.trendy.util.PreferenceKeys;
import com.temwa.tech.trendy.util.TrendyDeals;

import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class HomeFragment extends Fragment {


    private static final String TAG = "HomeFragment";

    //constants

    //widgets
    private RecyclerView mRecyclerView;

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

        findMatches();

        return view;
    }


   private void findMatches() {
       TrendyDeals users = new TrendyDeals();
        if (mMatches != null) {
            mMatches.clear();
        }
        for (TrendyDeal user : users.DEALS) {
            mMatches.add(user);
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
