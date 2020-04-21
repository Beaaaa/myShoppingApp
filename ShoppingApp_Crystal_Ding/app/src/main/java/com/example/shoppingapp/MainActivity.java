package com.example.shoppingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.shoppingapp.database.ItemData;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Item> dataItemList = ItemData.dataItemList;
    private CartManager cartManager;
    private Button tvButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cartManager = new CartManager(this);

        ItemAdapter adapter = new ItemAdapter(this, dataItemList);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);

        tvButton = findViewById(R.id.checkout);

        List<Order> storedOrders = cartManager.getOrders();
        if (storedOrders.size() == 0) {
            tvButton.setVisibility(View.INVISIBLE);
        }
        tvButton.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, CheckOut.class);
            MainActivity.this.startActivity(intent);
        }
    };
}
