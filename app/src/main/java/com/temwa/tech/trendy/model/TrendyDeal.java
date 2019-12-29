package com.temwa.tech.trendy.model;

import android.os.Parcel;
import android.os.Parcelable;

public class TrendyDeal implements Parcelable {

    private String deal_image;
    private String name;
    private String rating;
    private String no_of_reviews;
    //private String status;
    private String min_price;


    public TrendyDeal(String deal_image, String name, String rating, String no_of_reviews, String min_price) {
        this.deal_image = deal_image;
        this.name = name;
        this.rating = rating;
        this.no_of_reviews = no_of_reviews;
       // this.status = status;
        this.min_price = min_price;
    }

    public TrendyDeal() { }


    protected TrendyDeal(Parcel in) {
        deal_image = in.readString();
        name = in.readString();
        rating = in.readString();
        no_of_reviews = in.readString();
        //status = in.readString();
        min_price = in.readString();
    }


    public static final Creator<TrendyDeal> CREATOR = new Creator<TrendyDeal>() {
        @Override
        public TrendyDeal createFromParcel(Parcel in) {
            return new TrendyDeal(in);
        }

        @Override
        public TrendyDeal[] newArray(int size) {
            return new TrendyDeal[size];
        }
    };



    public String getDeal_image() {
        return deal_image;
    }

    public void setDeal_image(String deal_image) {
        this.deal_image = deal_image;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }


    public String getNo_of_reviews() {
        return no_of_reviews;
    }

    public void setNo_of_reviews(String no_of_reviews) {
        this.no_of_reviews = no_of_reviews;
    }


/*    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }*/


    public String getMin_price() {
        return min_price;
    }

    public void setMin_price(String min_price) {
        this.min_price = min_price;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(deal_image);
        parcel.writeString(name);
        parcel.writeString(rating);
        parcel.writeString(no_of_reviews);
       // parcel.writeString(status);
        parcel.writeString(min_price);
    }


}
