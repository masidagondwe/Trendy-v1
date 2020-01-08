package com.temwa.tech.trendy;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.like.LikeButton;
import com.like.OnLikeListener;
import com.temwa.tech.trendy.model.TrendyDeal;
import com.temwa.tech.trendy.util.PreferenceKeys;

import java.util.HashSet;
import java.util.Set;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class ViewTrendyDealFragment extends Fragment/* implements OnLikeListener */{


    private static final String TAG = "ViewTrendyDealFragment";

    //widgets
    private TextView mFragmentHeading, mName, mRating, mNoOfReviews, mMinPrice;
    private LikeButton mLikeButton;
    private RelativeLayout mBackArrow;
    private ImageView mDealImage;


    //vars
    private TrendyDeal mTrendyDeal;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if(bundle != null){
            mTrendyDeal = bundle.getParcelable(getString(R.string.intent_deal));
            Log.d(TAG, "onCreate: got incoming bundle: " + mTrendyDeal.getName());
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_trendy_deal, container, false);
        Log.d(TAG, "onCreateView: started.");
        mBackArrow = view.findViewById(R.id.back_arrow);
        mFragmentHeading = view.findViewById(R.id.fragment_heading);
        mDealImage = view.findViewById(R.id.deal_images);
        mLikeButton = view.findViewById(R.id.heart_button);
        mName = view.findViewById(R.id.deal_name);
        mRating = view.findViewById(R.id.deal_rating);
        mNoOfReviews = view.findViewById(R.id.no_of_deal_reviews);
        mMinPrice = view.findViewById(R.id.deal_min_price);


       /* mLikeButton.setOnLikeListener(this);*/
        checkIfSaved();
        //setBackgroundImage(view);
        init();

        return view;
    }

    private void init() {
        Log.d(TAG, "init: initializing " + getString(R.string.tag_fragment_saved_deals));
        if(mTrendyDeal != null){
            Glide.with(getActivity())
                    .load(mTrendyDeal.getDeal_image())
                    .into(mDealImage);

            mName.setText(mTrendyDeal.getName());
            mRating.setText(mTrendyDeal.getRating());
            mNoOfReviews.setText(mTrendyDeal.getNo_of_reviews());
            mMinPrice.setText(mTrendyDeal.getMin_price());
        }
    }

    private void checkIfSaved() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        Set<String> savedNames = preferences.getStringSet(PreferenceKeys.SAVED_DEALS, new HashSet<String>());
        if(savedNames.contains(mTrendyDeal.getName())){
            Log.d(TAG, "checkIfConnected: liked.");
           // mLikeButton.setLiked(true);
        }
        else{
            Log.d(TAG, "checkIfConnected: not liked.");
           // mLikeButton.setLiked(false);
        }
    }

/*
    @Override
    public void liked(LikeButton likeButton) {
        Log.d(TAG, "liked: liked");
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = preferences.edit();

        Set<String> savedNames = preferences.getStringSet(PreferenceKeys.SAVED_DEALS, new HashSet<String>());
        savedNames.add(mTrendyDeal.getName());
        editor.putStringSet(PreferenceKeys.SAVED_DEALS, savedNames);
        editor.commit();

    }

    @Override
    public void unLiked(LikeButton likeButton) {
        Log.d(TAG, "unLiked: unliked.");
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = preferences.edit();

        Set<String> savedNames = preferences.getStringSet(PreferenceKeys.SAVED_DEALS, new HashSet<String>());
        savedNames.remove(mTrendyDeal.getName());
        editor.remove(PreferenceKeys.SAVED_DEALS);
        editor.commit();
        editor.putStringSet(PreferenceKeys.SAVED_DEALS, savedNames);
        editor.commit();
    }
*/

}
