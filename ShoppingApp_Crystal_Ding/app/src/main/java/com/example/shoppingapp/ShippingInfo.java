package com.example.shoppingapp;

import android.support.annotation.NonNull;

public class ShippingInfo {

    private String receiver;
    private String address;

    public ShippingInfo(String name, String address) {
        this.receiver = name;
        this.address = address;
    }

    @NonNull
    @Override
    public String toString() {
        return  ("Shipping Info received - Receiver: " + receiver +
                "; Address: " + address);
    }
}
