package com.temwa.tech.trendy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.temwa.tech.trendy.model.TrendyDeal;
import com.temwa.tech.trendy.util.FirebaseUtil;

public class DealActivity extends AppCompatActivity {

    protected FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private static final int PICTURE_RESULT = 42; //the answer to everything
    TextView txtDealName;
    TextView txtDealDescription;
    TextView txtDealRating;
    TextView txtDealReviews;
    TextView txtDealPrice;
    ImageView imgDealView;
    TrendyDeal mTrendyDeal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal);

        mFirebaseDatabase = FirebaseUtil.mFirebaseDatabase;
        mDatabaseReference = FirebaseUtil.mDatabaseReference;

        txtDealName = findViewById(R.id.add_deal_name);
        txtDealDescription = findViewById(R.id.add_deal_description);
        txtDealRating = findViewById(R.id.add_deal_rating);
        txtDealReviews  = findViewById(R.id.add_deal_reviews);
        txtDealPrice = findViewById(R.id.text_min_price);
        imgDealView = findViewById(R.id.deal_image_1);

        Intent intent = getIntent();
        TrendyDeal deal = (TrendyDeal) intent.getSerializableExtra("Deal");
        if (deal==null) {
            deal = new TrendyDeal();
        }

        this.mTrendyDeal = deal;

        txtDealName.setText(deal.getName());
        txtDealDescription.setText(deal.getDescription());
        txtDealRating.setText(deal.getRating());
        txtDealReviews.setText(deal.getNo_of_reviews());
        txtDealPrice.setText(deal.getMin_price());

        showImage(deal.getDeal_image());
        Button btnImage = findViewById(R.id.btnImage);
        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/jpeg");
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(intent.createChooser(intent,
                        "Insert Picture"), PICTURE_RESULT);
            }
        });
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
    }*/

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
                    mTrendyDeal.setImageName(pictureName);
                    // ref.getPath();
                    ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            mTrendyDeal.setImageUrl(uri.toString());
                            showImage(uri.toString());
                        }
                    });
                }
            });
        }
    }

    private void saveDeal() {
        mTrendyDeal.setName(txtDealName.getText().toString());
        mTrendyDeal.setDescription(txtDealName.getText().toString());
        mTrendyDeal.setRating(txtDealRating.getText().toString());
        mTrendyDeal.setNo_of_reviews(txtDealReviews.getText().toString());
        mTrendyDeal.setMin_price(txtDealPrice.getText().toString());

        if(mTrendyDeal.getID()==null) {
            mDatabaseReference.push().setValue(mTrendyDeal);
        }
        else {
            mDatabaseReference.child(mTrendyDeal.getID()).setValue(mTrendyDeal);
        }
    }
    private void deleteDeal() {
        if (mTrendyDeal == null) {
            Toast.makeText(this, "Please save the deal before deleting", Toast.LENGTH_SHORT).show();
            return;
        }
        mDatabaseReference.child(mTrendyDeal.getId()).removeValue();
        Log.d("image name", mTrendyDeal.getImageName());
        if(mTrendyDeal.getImageName() != null && mTrendyDeal.getImageName().isEmpty() == false) {
            StorageReference picRef = FirebaseUtil.mStorage.getReference().child(mTrendyDeal.getImageName());
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
