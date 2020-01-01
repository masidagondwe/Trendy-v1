package com.temwa.tech.trendy;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.temwa.tech.trendy.model.TrendyDeal;
import com.temwa.tech.trendy.util.PreferenceKeys;
import com.temwa.tech.trendy.util.TrendyDeals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class SavedDealsFragment extends Fragment {
    private static final String TAG = "SavedDealsFragment";

    //constants
    private static final int NUM_GRID_COLUMNS = 2;

    //widgets
    private TrendyDealAdapter mRecyclerViewAdapter;
    private RecyclerView mRecyclerView;

    //vars
    private ArrayList<TrendyDeal> mTrendyDeals = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_saved_deals, container, false);
        Log.d(TAG, "onCreateView: started.");

        mRecyclerView = view.findViewById(R.id.recycler_view_deals);

        getConnections();

        return view;
    }

    private void getConnections() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        Set<String> savedNames = preferences.getStringSet(PreferenceKeys.SAVED_DEALS, new HashSet<String>());

        TrendyDeals deals = new TrendyDeals();
        if(mTrendyDeals != null){
            mTrendyDeals.clear();
        }
        for(TrendyDeal deal: deals.DEALS){
            if(savedNames.contains(deal.getName())){
                mTrendyDeals.add(deal);
            }
        }
        if(mRecyclerViewAdapter == null){
            initRecyclerView();
        }
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        mRecyclerViewAdapter = new TrendyDealAdapter(getActivity(), mTrendyDeals);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(NUM_GRID_COLUMNS, LinearLayoutManager.VERTICAL);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
    }

}
