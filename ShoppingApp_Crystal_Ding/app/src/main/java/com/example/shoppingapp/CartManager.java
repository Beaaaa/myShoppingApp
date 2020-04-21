package com.example.shoppingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.shoppingapp.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class CartManager {

    private DatabaseHelper dbHelper;

    public CartManager(Context context) {
        dbHelper = DatabaseHelper.getInstance(context);
    }

    public List<Order> getOrders() {

        List<Order> orders = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + DatabaseHelper.TABLE_NAME,
                null
        );

        if(cursor.moveToFirst()) {
            while(!cursor.isAfterLast()) {

                Order order = new Order(
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.ID)),
                        cursor.getInt(cursor.getColumnIndex(DatabaseHelper.QTY))
                );
                orders.add(order);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return orders;
    }

    public void addOrder(Order order) {

        ContentValues newOrder = new ContentValues();
        newOrder.put(DatabaseHelper.ID, order.getId());
        newOrder.put(DatabaseHelper.QTY, order.getQuantity());

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.insert(DatabaseHelper.TABLE_NAME, null, newOrder);
    }

    public void clearCart() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("DELETE FROM " + DatabaseHelper.TABLE_NAME);
    }
}
