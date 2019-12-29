package com.temwa.tech.trendy;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import de.hdodenhof.circleimageview.CircleImageView;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.temwa.tech.trendy.model.User;
import com.temwa.tech.trendy.util.PreferenceKeys;

import java.util.Set;


public class ViewProfileFragment extends Fragment {

    private static final String TAG = "ViewProfileFragment";

    //widgets
    private TextView mFragmentHeading, mName, mInterestedIn, mStatus;
    private CircleImageView mProfileImage;

    //vars
    private User mUser;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if(bundle != null){
            mUser = bundle.getParcelable(getString(R.string.intent_user));
            Log.d(TAG, "onCreate: got incoming bundle: " + mUser.getName());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_profile, container, false);
        Log.d(TAG, "onCreateView: started.");
        //mBackArrow = view.findViewById(R.id.back_arrow);
        mFragmentHeading = view.findViewById(R.id.fragment_heading);
        mProfileImage = view.findViewById(R.id.profile_image);
        //mLikeButton = view.findViewById(R.id.heart_button);
        mName = view.findViewById(R.id.name);
        mInterestedIn = view.findViewById(R.id.interested_in);
        mStatus = view.findViewById(R.id.status);


        //mLikeButton.setOnLikeListener(this);
        //checkIfConnected();
        //setBackgroundImage(view);
        init();

        return view;
    }


    private void init(){
        Log.d(TAG, "init: initializing " + getString(R.string.tag_fragment_view_profile));
        if(mUser != null){
            Glide.with(getActivity())
                    .load(mUser.getProfile_image())
                    .into(mProfileImage);

            mName.setText(mUser.getName());
            mInterestedIn.setText(mUser.getInterested_in());
            mStatus.setText(mUser.getStatus());
        }
    }

/*    private void setBackgroundImage(View view){
        ImageView backgroundView = view.findViewById(R.id.background);
        Glide.with(getActivity())
                .load(Resources.BACKGROUND_HEARTS)
                .into(backgroundView);
    }*/

/*    private void checkIfConnected(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        Set<String> savedNames = preferences.getStringSet(PreferenceKeys.SAVED_CONNECTIONS, new HashSet<String>());
        if(savedNames.contains(mUser.getName())){
            Log.d(TAG, "checkIfConnected: liked.");
            //mLikeButton.setLiked(true);
        }
        else{
            Log.d(TAG, "checkIfConnected: not liked.");
            //mLikeButton.setLiked(false);
        }
    }*/

}
