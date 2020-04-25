package com.temwa.tech.trendy.model;

import android.os.Parcel;
import android.os.Parcelable;

public class TrendyDeal implements Parcelable {

    private String id;
    //private String deal_image;
    private String name;
    private String description;
    private String rating;
    private String no_of_reviews;
    private String min_price;
    private String imageUrl;
    private String imageName;

    private String notification;

    public TrendyDeal(String id, String name, String description, String rating, String no_of_reviews, String min_price, String imageUrl, String imageName) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.no_of_reviews = no_of_reviews;
        this.description = description;
        this.min_price = min_price;
        this.imageUrl = imageUrl;
        this.imageName = imageName;
    }


    public TrendyDeal(String deal_image, String name, String notification) {
        //this.deal_image = deal_image;
        this.name = name;
        this.notification = notification;
    }

    public TrendyDeal() { }


    protected TrendyDeal(Parcel in) {
        //deal_image = in.readString();
        name = in.readString();
        rating = in.readString();
        no_of_reviews = in.readString();
        description = in.readString();
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

    public String getID() {
        return id;
    }

    public void setID(String ID) {
        this.id = ID;
    }


    public String getDeal_image() {
        return imageName;
    }

    public void setDeal_image(String deal_image) {
        this.imageName = deal_image;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
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


    public String getDescription() {
        return description;
    }

    public void setDescription(String status) {
        this.description = description;
    }


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
        parcel.writeString(imageName);
        parcel.writeString(name);
        parcel.writeString(rating);
        parcel.writeString(no_of_reviews);
        parcel.writeString(description);
        parcel.writeString(min_price);
    }


    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }
}
