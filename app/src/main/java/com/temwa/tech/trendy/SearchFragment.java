package com.temwa.tech.trendy;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.temwa.tech.trendy.model.TrendyDeal;
import com.temwa.tech.trendy.util.TrendyDeals;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;


public class SearchFragment extends Fragment {

    private static final String TAG = "SearchFragment";

    //constants
    private static final int NUM_GRID_COLUMNS = 2;

    //widgets
    private TrendySearchAdapter mRecyclerViewAdapter;
    private RecyclerView mRecyclerView;
    private SearchView mSearchView;

    //vars
    private ArrayList<TrendyDeal> mTrendyDeals = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        Log.d(TAG, "onCreateView: started.");

        mRecyclerView = view.findViewById(R.id.search_recycler_view);
        mSearchView = (SearchView) view.findViewById(R.id.action_search);

        getConnections();
        initSearchView();

        return view;
    }


    private void initSearchView(){
        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        mSearchView.setSearchableInfo(searchManager
                .getSearchableInfo(getActivity().getComponentName()));
        mSearchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                mRecyclerViewAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                mRecyclerViewAdapter.getFilter().filter(query);
                return false;
            }
        });
    }


    private void getConnections() {
       /* SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        Set<String> savedNames = preferences.getStringSet(PreferenceKeys.SAVED_DEALS, new HashSet<String>());
*/
        TrendyDeals deals = new TrendyDeals();
        if(mTrendyDeals != null){
            mTrendyDeals.clear();
        }
        for(TrendyDeal deal: deals.DEALS){
           // if(savedNames.contains(deal.getName())){
                mTrendyDeals.add(deal);
           // }
        }
        if(mRecyclerViewAdapter == null){
            initRecyclerView();
        }
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        mRecyclerViewAdapter = new TrendySearchAdapter(getActivity(), mTrendyDeals);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(NUM_GRID_COLUMNS, LinearLayoutManager.VERTICAL);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
    }
}
