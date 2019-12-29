package com.temwa.tech.trendy;



import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.temwa.tech.trendy.model.TrendyDeal;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class TrendyDealAdapter extends RecyclerView.Adapter<TrendyDealAdapter.TrendyDealViewHolder>{

    private static final String TAG = "TrendyDealAdapter";

/*    ArrayList<TrendyDeal> deals;
    private ImageView imageDeal;*/

    //vars
    private ArrayList<TrendyDeal> mTrendyDeals = new ArrayList<>();
    private Context mContext;
    private IMainActivity mInterface;


    public TrendyDealAdapter(){}

    public TrendyDealAdapter(Context context, ArrayList<TrendyDeal> trendyDeals) {
        mContext = context;
        mTrendyDeals = trendyDeals;
    }


    @Override
    public TrendyDealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_main_feed, parent, false);
        TrendyDealViewHolder holder = new TrendyDealViewHolder(view);
        return holder;

        /* Context context = parent.getContext();
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.rv_row, parent, false);
        return new TrendyDealViewHolder(itemView);*/
    }

    @Override
    public void onBindViewHolder(@NonNull TrendyDealViewHolder holder, final int position) {

        Log.d(TAG, "onBindViewHolder: called.");

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background);

        Glide.with(mContext)
                .load(mTrendyDeals.get(position).getDeal_image())
                .apply(requestOptions)
                .into(holder.imageDeal);

        holder.tvName.setText(mTrendyDeals.get(position).getName());
        holder.tvRating.setText(mTrendyDeals.get(position).getRating());
        holder.tvMinPrice.setText(mTrendyDeals.get(position).getMin_price());
        holder.tvNoOfReviews.setText(mTrendyDeals.get(position).getNo_of_reviews());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on: " + mTrendyDeals.get(position).getName());

                mInterface.inflateViewTrendyDealFragment(mTrendyDeals.get(position));
            }
        });

       /* TrendyDeal deal = deals.get(position);
        holder.bind(deal);*/
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mInterface = (IMainActivity) mContext;
    }

    @Override
    public int getItemCount() {
        return  mTrendyDeals.size();
        //return 0;//deals.size();
    }



        /*private String deal_image;
        private String name;
        private String rating;
        private String no_of_reviews;
        private String min_price;*/

    public class TrendyDealViewHolder extends RecyclerView.ViewHolder/* implements View.OnClickListener */{
        ImageView imageDeal;
        TextView tvName;
        TextView tvRating;
        TextView tvNoOfReviews;
        TextView tvMinPrice;
        CardView cardView;

        public TrendyDealViewHolder(View itemView) {
            super(itemView);
            imageDeal = itemView.findViewById(R.id.deal_image);
            tvName = itemView.findViewById(R.id.name);
            tvRating = itemView.findViewById(R.id.rating);
            tvNoOfReviews = itemView.findViewById(R.id.no_of_reviews);
            tvMinPrice = itemView.findViewById(R.id.min_price);
            cardView = itemView.findViewById(R.id.card_view);
        }

        /*TextView tvTitle;
        TextView tvDescription;
        TextView tvPrice;

        public TrendyDealViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            imageDeal = itemView.findViewById(R.id.imageDeal);
            itemView.setOnClickListener(this);
        }


        public void bind(TrendyDeal deal) {
            tvName.setText(deal.getName());
            tvRating.setText(deal.getRating());
            tvNoOfReviews.setText(deal.getNo_of_reviews());
            tvMinPrice.setText(deal.getMin_price());
            showImage(deal.getDeal_image());
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Log.d("Click", String.valueOf(position));
            TrendyDeal selectedDeal = deals.get(position);
            Intent intent = new Intent(view.getContext(), HomeFragment.class);
            intent.putExtra("Deal", selectedDeal);
            view.getContext().startActivity(intent);
        }

        private void showImage(String url) {
            if (url != null && url.isEmpty()==false) {
                Picasso.get()
                        .load(url)
                        .resize(160, 160)
                        .centerCrop()
                        .into(imageDeal);
            }
        }*/
    }
}
