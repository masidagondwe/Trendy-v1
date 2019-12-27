package com.temwa.tech.trendy;



import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.temwa.tech.trendy.model.TrendyDeal;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TrendyDealAdapter extends RecyclerView.Adapter<TrendyDealAdapter.TrendyDealViewHolder>{

    ArrayList<TrendyDeal> deals;
    private ImageView imageDeal;

    @NonNull
    @Override
    public TrendyDealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.rv_row, parent, false);
        return new TrendyDealViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TrendyDealViewHolder holder, int position) {

        TrendyDeal deal = deals.get(position);
        holder.bind(deal);
    }


    @Override
    public int getItemCount() {
        return 0;//deals.size();
    }


    public class TrendyDealViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        TextView tvTitle;
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


        /*private String deal_image;
        private String name;
        private String rating;
        private String no_of_reviews;
        private String status;
        private String min_price;*/

        public void bind(TrendyDeal deal) {
            tvTitle.setText(deal.getName());
            tvDescription.setText(deal.getRating());
            tvPrice.setText(deal.getNo_of_reviews());
            tvPrice.setText(deal.getStatus());
            tvPrice.setText(deal.getMin_price());
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
        }
    }
}
