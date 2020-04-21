package com.example.shoppingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Locale;

public class ShippingDetail extends AppCompatActivity {

    private CartManager cartManager;
    private double total_after_shipping;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping_detail);

        cartManager = new CartManager(this);

        TextView tvTotal = findViewById(R.id.tvTotal);
        Button tvButton = findViewById(R.id.tvConfirm);

        total_after_shipping = getIntent().getDoubleExtra("total_before_shipping", 0.0) + 10.0;
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.getDefault());
        tvTotal.setText(nf.format(total_after_shipping));

        tvButton.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {

            EditText tvReceiver = findViewById(R.id.tvReceiver);
            EditText tvAddress = findViewById(R.id.tvAddress);
            TextView errMsg = findViewById(R.id.errorMsg);

            String receiver = tvReceiver.getText().toString();
            String address = tvAddress.getText().toString();

            if (receiver.isEmpty() || address.isEmpty()) {
                errMsg.setText("Shipping info cannot be empty");
                errMsg.setVisibility(View.VISIBLE);
            }
            else {
                ShippingInfo shippingInfo = new ShippingInfo(receiver, address);

                Toast.makeText(ShippingDetail.this, shippingInfo.toString(),
                        Toast.LENGTH_LONG).show();

                cartManager.clearCart();

                Intent i=new Intent(ShippingDetail.this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        }
    };
}
