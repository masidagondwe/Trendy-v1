package com.temwa.tech.trendy;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import androidx.*;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class HomeFragment extends Fragment {


    private static final String TAG = "HomeFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Log.d(TAG, "onCreateView: started.");
        //mRecyclerView = view.findViewById(R.id.recycler_view);

        //findMatches();

        return view;
    }


}
