package com.example.shoppingapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shoppingapp.database.ItemData;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class OrderAdapter extends ArrayAdapter<Order> {

    private List<Item> dataItemList = ItemData.dataItemList;
    private Context oContext;
    private List<Order> oOrders;
    private LayoutInflater oInflater;
    private double total;

    public OrderAdapter(Context context, List<Order> orders) {
        super(context, R.layout.checkout_list, orders);

        oContext = context;
        oOrders = orders;
        oInflater = LayoutInflater.from(context);
    }

    public double getTotal() {
        for (int i = 0; i < dataItemList.size(); i++) {
            for (int j = 0; j < oOrders.size(); j++) {
                if (dataItemList.get(i).getItemId().equals(oOrders.get(j).getId())) {
                    total += dataItemList.get(i).getPrice() * oOrders.get(j).getQuantity();
                }
            }
        }
        return total;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        final OrderViewHolder holder;

        if (convertView == null) {
            convertView = oInflater.inflate(R.layout.checkout_list, parent, false);
            holder = new OrderViewHolder();
            holder.itemName = convertView.findViewById(R.id.itemName);
            holder.itemQty = convertView.findViewById(R.id.itemQty);
            holder.itemImage = convertView.findViewById(R.id.itemImage);
            holder.itemPrice = convertView.findViewById(R.id.itemPrice);
            convertView.setTag(holder);

            for (int i = 0; i < dataItemList.size(); i++) {
                if (oOrders.get(position).getId().equals(dataItemList.get(i).getItemId())) {

                    double price = dataItemList.get(i).getPrice();
                    int qty = oOrders.get(position).getQuantity();

                    holder.itemName.setText(dataItemList.get(i).getItemName());
                    holder.itemQty.setText(String.valueOf(qty));

                    NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.getDefault());
                    holder.itemPrice.setText(nf.format(price));

                    InputStream inputStream = null;

                    try {
                        String imageFile = dataItemList.get(i).getImage();
                        inputStream = getContext().getAssets().open(imageFile);
                        Drawable d = Drawable.createFromStream(inputStream, null);
                        holder.itemImage.setImageDrawable(d);
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                    finally {
                        try {
                            if (inputStream != null) {
                                inputStream.close();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                }
            }
        }
        else {
            holder = (OrderViewHolder) convertView.getTag();
        }

        return convertView;
    }

    public static class OrderViewHolder {
        public TextView itemName;
        public TextView itemQty;
        public TextView itemPrice;
        public ImageView itemImage;
    }
}
