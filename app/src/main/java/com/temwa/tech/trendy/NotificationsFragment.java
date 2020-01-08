package com.temwa.tech.trendy;

import android.content.SharedPreferences;
import android.os.Bundle;
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

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class NotificationsFragment extends Fragment {

    private static final String TAG = "NotificationsFragment";

    //widgets
    private RecyclerView mRecyclerView;
    private TrendyNotificationsAdapter mRecyclerViewAdapter;
    //vars
    private ArrayList<TrendyDeal> mDeals = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);
        Log.d(TAG, "onCreateView: started.");
        mRecyclerView = view.findViewById(R.id.notifications_recycler_view);

        getConnections();

        return view;
    }


    private void getConnections() {
        //SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        //Set<String> savedNames = preferences.getStringSet(PreferenceKeys.SAVED_DEALS, new HashSet<String>());
        TrendyDeals deals = new TrendyDeals();
        if(mDeals != null){
            mDeals.clear();
        }
        for(TrendyDeal deal: deals.DEALS){
            //if(savedNames.contains(deal.getName())){
                mDeals.add(deal);
           // }
        }
        if(mRecyclerViewAdapter == null){
            initRecyclerView();
        }
    }





    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview. notifications");
        mRecyclerViewAdapter = new TrendyNotificationsAdapter(getActivity(), mDeals);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
