package com.example.shoppingapp;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.Locale;

public class DisplayItemDetail extends AppCompatActivity {

    private CartManager cartManager;
    private TextView tvName, tvPrice, tvDescription;
    private ImageView itemImage;
    private EditText tvQty;
    private Button tvButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        final Item item = getIntent().getExtras().getParcelable(ItemAdapter.ITEM_KEY);
        if (item == null) {
            throw new AssertionError("Null data item received!");
        }

        cartManager = new CartManager(this);

        tvName = findViewById(R.id.tvItemName);
        tvPrice = findViewById(R.id.tvPrice);
        tvDescription = findViewById(R.id.tvDescription);
        itemImage = findViewById(R.id.itemImage);
        tvQty = findViewById(R.id.tvQty);
        tvButton = findViewById(R.id.addToCart);

        tvName.setText(item.getItemName());
        tvDescription.setText(item.getDescription());

        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.getDefault());
        tvPrice.setText(nf.format(item.getPrice()));

        InputStream inputStream = null;
        try {
            String imageFile = item.getImage();
            inputStream = getAssets().open(imageFile);
            Drawable d = Drawable.createFromStream(inputStream, null);
            itemImage.setImageDrawable(d);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        tvButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int qty = Integer.parseInt(tvQty.getText().toString());
                Order order = new Order(item.getItemId(), qty);
                cartManager.addOrder(order);

                Intent intent = new Intent(DisplayItemDetail.this, MainActivity.class);
                DisplayItemDetail.this.startActivity(intent);
            }
        });
    }
}
