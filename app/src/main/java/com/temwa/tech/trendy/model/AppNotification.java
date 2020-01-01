package com.temwa.tech.trendy.model;

import android.os.Parcel;
import android.os.Parcelable;

public class AppNotification implements Parcelable {

    private TrendyDeal trendyDeal;
    private String appNotification;

    public AppNotification() {}

    public AppNotification(TrendyDeal trendyDeal, String appNotification) {
        this.trendyDeal = trendyDeal;
        this.appNotification = appNotification;
    }

    private AppNotification(Parcel in){
        trendyDeal = in.readParcelable(TrendyDeal.class.getClassLoader());
        appNotification = in.readString();
    }

    public static final Creator<AppNotification> CREATOR = new Creator<AppNotification>() {
        @Override
        public AppNotification createFromParcel(Parcel in) {
            return new AppNotification(in);
        }

        @Override
        public AppNotification[] newArray(int size) {
            return new AppNotification[size];
        }
    };

    public TrendyDeal getDeal() {
        return trendyDeal;
    }

    public void setDeal(TrendyDeal trendyDeal) {
        this.trendyDeal = trendyDeal;
    }

    public String getMessage() {
        return appNotification;
    }

    public void setMessage(String appNotification) {
        this.appNotification = appNotification;
    }

    @Override
    public String toString() {
        return "Message{" +
                "deal=" + trendyDeal +
                ", notification='" + appNotification + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(trendyDeal, i);
        parcel.writeString(appNotification);
    }
}
