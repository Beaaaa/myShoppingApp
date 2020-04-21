package com.example.shoppingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class CheckOut extends AppCompatActivity {

    public static final String DOUBLE_KEY = "total_before_shipping";
    private CartManager cartManager;
    private double total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        ListView orderList = findViewById(R.id.checkout_list);

        cartManager = new CartManager(this);
        OrderAdapter adapter = new OrderAdapter(this, cartManager.getOrders());
        orderList.setAdapter(adapter);

        TextView tvTotal = findViewById(R.id.tvTotal);
        total = adapter.getTotal();
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.getDefault());
        tvTotal.setText(nf.format(total));

        Button tvButton = findViewById(R.id.tvNext);
        tvButton.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(CheckOut.this, ShippingDetail.class);
            intent.putExtra(DOUBLE_KEY, total);
            CheckOut.this.startActivity(intent);
        }
    };
}
