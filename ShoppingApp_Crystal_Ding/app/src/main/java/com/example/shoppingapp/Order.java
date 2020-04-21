package com.example.shoppingapp;

public class Order {
    private String id;
    private int quantity;

    public Order(String id, int qty) {
        this.id = id;
        this.quantity = qty;
    }

    public String getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }
}
