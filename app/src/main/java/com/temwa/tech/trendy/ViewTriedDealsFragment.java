package com.temwa.tech.trendy;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.temwa.tech.trendy.model.TrendyDeal;
import com.temwa.tech.trendy.util.TrendyDeals;

import java.util.ArrayList;


public class ViewTriedDealsFragment extends Fragment {

    private static final String TAG = "ViewTriedDealsFragment";

    //widgets
    private RecyclerView mRecyclerView;

    //vars
    private ArrayList<TrendyDeal> mMatches = new ArrayList<>();
    private TrendyDealAdapter mRecyclerViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_tried_deals, container, false);
        Log.d(TAG, "onCreateView: started.");
        mRecyclerView = view.findViewById(R.id.tried_deals_recycler_view);

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

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        mRecyclerViewAdapter = new TrendyDealAdapter(getActivity(), mMatches);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Log.d(TAG, "initRecyclerView: init recyclerview end.");
    }
}
