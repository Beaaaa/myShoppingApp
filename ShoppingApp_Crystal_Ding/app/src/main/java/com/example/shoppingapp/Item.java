package com.example.shoppingapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

public class Item implements Parcelable {
    private String itemId;
    private String itemName;
    private double price;
    private String image;
    private String description;

    public Item(String itemId, String itemName, double price, String image, String description) {

        if (itemId == null) {
            itemId = UUID.randomUUID().toString();
        }

        this.itemId = itemId;
        this.itemName = itemName;
        this.price = price;
        this.image = image;
        this.description = description;
    }

    public String getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public double getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.itemId);
        dest.writeString(this.itemName);
        dest.writeDouble(this.price);
        dest.writeString(this.image);
        dest.writeString(this.description);
    }

    protected Item(Parcel in) {
        this.itemId = in.readString();
        this.itemName = in.readString();
        this.price = in.readDouble();
        this.image = in.readString();
        this.description = in.readString();
    }

    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel source) {
            return new Item(source);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}
