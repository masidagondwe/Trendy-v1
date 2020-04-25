package com.temwa.tech.trendy;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.temwa.tech.trendy.model.TrendyDeal;
import com.temwa.tech.trendy.util.FirebaseUtil;
import com.temwa.tech.trendy.util.PreferenceKeys;
import com.temwa.tech.trendy.util.TrendyDeals;

import java.util.HashSet;
import java.util.Set;

import static com.firebase.ui.auth.AuthUI.TAG;


public class AddDealFragment extends Fragment {

    private static final String TAG = "AddDealFragment";

    protected FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private static final int PICTURE_RESULT = 42; //the answer to everything

    EditText txtTitle;
    EditText txtDescription;
    EditText txtPrice;
    EditText txtHeading;
    ImageView imageView;
    TrendyDeal mTrendyDeal;

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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_menu:
                saveDeal();
                Toast.makeText(this, "Deal saved", Toast.LENGTH_LONG).show();
                clean();
                backToList();
                return true;
            case R.id.delete_menu:
                deleteDeal();
                Toast.makeText(this, "Deal Deleted", Toast.LENGTH_LONG).show();
                backToList();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_menu, menu);
        if (FirebaseUtil.isAdmin) {
            menu.findItem(R.id.delete_menu).setVisible(true);
            menu.findItem(R.id.save_menu).setVisible(true);
            enableEditTexts(true);
            txtHeading.setText("Edit Travel Deal");
        }
        else {
            menu.findItem(R.id.delete_menu).setVisible(false);
            menu.findItem(R.id.save_menu).setVisible(false);
            enableEditTexts(false);
            findViewById(R.id.btnImage).setVisibility(View.GONE);
            txtHeading.setText("View Travel Deal");
        }
        return true;
    }
*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICTURE_RESULT && resultCode == RESULT_OK) {
            final Uri imageUri = data.getData();
            final StorageReference ref = FirebaseUtil.mStorageRef.child(imageUri.getLastPathSegment());

            ref.putFile(imageUri).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    String pictureName = taskSnapshot.getStorage().getPath();
                    deal.setImageName(pictureName);
                    // ref.getPath();
                    ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            deal.setImageUrl(uri.toString());
                            showImage(uri.toString());
                        }
                    });
                }
            });
        }
    }

    private void saveDeal() {
        deal.setTitle(txtTitle.getText().toString());
        deal.setDescription(txtDescription.getText().toString());
        deal.setPrice(txtPrice.getText().toString());

        if(deal.getId()==null) {
            mDatabaseReference.push().setValue(deal);
        }
        else {
            mDatabaseReference.child(deal.getId()).setValue(deal);
        }
    }
    private void deleteDeal() {
        if (deal == null) {
            Toast.makeText(this, "Please save the deal before deleting", Toast.LENGTH_SHORT).show();
            return;
        }
        mDatabaseReference.child(deal.getId()).removeValue();
        Log.d("image name", deal.getImageName());
        if(deal.getImageName() != null && deal.getImageName().isEmpty() == false) {
            StorageReference picRef = FirebaseUtil.mStorage.getReference().child(deal.getImageName());
            picRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Log.d("Delete Image", "Image Successfully Deleted");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("Delete Image", e.getMessage());
                }
            });
        }

    }
    private void backToList() {
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }
    private void clean() {
        txtTitle.setText("");
        txtPrice.setText("");
        txtDescription.setText("");
        txtTitle.requestFocus();
    }
    private void enableEditTexts(boolean isEnabled) {
        txtTitle.setEnabled(isEnabled);
        txtDescription.setEnabled(isEnabled);
        txtPrice.setEnabled(isEnabled);
    }
    private void showImage(String url) {
        if (url != null && url.isEmpty() == false) {
            int width = Resources.getSystem().getDisplayMetrics().widthPixels;
            Picasso.get()
                    .load(url)
                    .resize(width, width*2/3)
                    .centerCrop()
                    .into(imageView);
        }
    }

}
