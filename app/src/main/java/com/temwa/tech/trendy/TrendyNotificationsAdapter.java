package com.temwa.tech.trendy;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.temwa.tech.trendy.model.AppNotification;
import com.temwa.tech.trendy.model.TrendyDeal;
import com.temwa.tech.trendy.util.AppNotifications;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class TrendyNotificationsAdapter extends RecyclerView.Adapter<TrendyNotificationsAdapter.TrendyNotificationsViewHolder>{

    private static final String TAG = "NotificationsAdapter";

    //vars
    private ArrayList<TrendyDeal> mTrendyDeals;
    private Context mContext;
    private IMainActivity mInterface;


    //public TrendyNotificationsAdapter(){}

    public TrendyNotificationsAdapter(Context context, ArrayList<TrendyDeal> trendyDeals) {
        mContext = context;
        mTrendyDeals = trendyDeals;
    }


    @Override
    public TrendyNotificationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_notifications, parent, false);
        TrendyNotificationsViewHolder holder = new TrendyNotificationsViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull TrendyNotificationsViewHolder holder, final int position) {

        //Log.d(TAG, "on");

        final TrendyDeal trendyDeal = mTrendyDeals.get(position);

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background);

        Glide.with(mContext)
                .load(mTrendyDeals.get(position).getDeal_image())
                .apply(requestOptions)
                .into(holder.imageDeal);

        holder.tvName.setText(mTrendyDeals.get(position).getName());
        holder.tvNotification.setText(mTrendyDeals.get(position).getNotification());

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on: " + mTrendyDeals.get(position).getName());

                mInterface.onNotificationSelected(new AppNotification(trendyDeal, AppNotifications.NOTIFICATIONS[position]));
            }
        });
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


    public class TrendyNotificationsViewHolder extends RecyclerView.ViewHolder/* implements View.OnClickListener */{
        ImageView imageDeal;
        TextView tvName;
        TextView tvNotification;
        RelativeLayout parent;

        public TrendyNotificationsViewHolder(View itemView) {
            super(itemView);
            imageDeal = itemView.findViewById(R.id.notification_image);
            tvName = itemView.findViewById(R.id.notification_name);
            tvNotification = itemView.findViewById(R.id.notification_text);
            parent = itemView.findViewById(R.id.parent_view);
        }
    }
}
