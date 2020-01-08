package com.temwa.tech.trendy;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.temwa.tech.trendy.model.TrendyDeal;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class TrendySearchAdapter extends RecyclerView.Adapter<TrendySearchAdapter.SearchViewHolder>{

    private static final String TAG = "Search Adapter";

    //vars
    private ArrayList<TrendyDeal> mTrendyDeals = new ArrayList<>();
    private ArrayList<TrendyDeal> mFilteredDeals = new ArrayList<>();
    private Context mContext;
    private IMainActivity mInterface;


    public TrendySearchAdapter(){}

    public TrendySearchAdapter(Context context, ArrayList<TrendyDeal> trendyDeals) {
        mContext = context;
        mTrendyDeals = trendyDeals;
    }


    @Override
    public TrendySearchAdapter.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_main_feed, parent, false);
        TrendySearchAdapter.SearchViewHolder holder = new TrendySearchAdapter.SearchViewHolder(view);
        return holder;

        /* Context context = parent.getContext();
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.rv_row, parent, false);
        return new SearchViewHolder(itemView);*/
    }

    @Override
    public void onBindViewHolder(@NonNull TrendySearchAdapter.SearchViewHolder holder, final int position) {

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


    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mFilteredDeals = mTrendyDeals;
                } else {
                    ArrayList<TrendyDeal> filteredList = new ArrayList<>();
                    for (TrendyDeal row : mTrendyDeals) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    mFilteredDeals = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredDeals;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredDeals = (ArrayList<TrendyDeal>) filterResults.values;

                // refresh the list with filtered data
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mInterface = (IMainActivity) mContext;
    }

    @Override
    public int getItemCount() {
        return  mFilteredDeals.size();
    }


    public class SearchViewHolder extends RecyclerView.ViewHolder/* implements View.OnClickListener */ {
        ImageView imageDeal;
        TextView tvName;
        TextView tvRating;
        TextView tvNoOfReviews;
        TextView tvMinPrice;
        CardView cardView;

        public SearchViewHolder(View itemView) {
            super(itemView);
            imageDeal = itemView.findViewById(R.id.deal_image);
            tvName = itemView.findViewById(R.id.name);
            tvRating = itemView.findViewById(R.id.rating);
            tvNoOfReviews = itemView.findViewById(R.id.no_of_reviews);
            tvMinPrice = itemView.findViewById(R.id.min_price);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }
}
