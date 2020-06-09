package com.vishnu.fugo_login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    RecyclerView recycle;
    Button pay;

    ArrayList<String> TotalPrice;

    String Items[], Prices[];
    int images[] = {R.drawable.masaladosa, R.drawable.setdosa, R.drawable.openbutter,
            R.drawable.idli, R.drawable.vada, R.drawable.chole,
            R.drawable.vegbiryani, R.drawable.aloo,
            R.drawable.vegfried, R.drawable.coffee, R.drawable.tea};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recycle = findViewById(R.id.cart_recycler);
        pay = findViewById(R.id.paybtn);

        Intent cartIntent = getIntent();
        cartIntent.getExtras();

        final ArrayList<String> items = (ArrayList<String>) getIntent().getSerializableExtra("Items");
        ArrayList<String> prices = (ArrayList<String>) getIntent().getSerializableExtra("Prices");
        ArrayList<Integer> images = (ArrayList<Integer>) getIntent().getSerializableExtra("Image");


        recycle.setLayoutManager(new LinearLayoutManager(CartActivity.this));
        final CartViewAdapter myadapter = new CartViewAdapter(this, items, prices, images);
        recycle.setAdapter(myadapter);

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String testString = myadapter.test;

                ArrayList<Integer> total = myadapter.Total;
                ArrayList<String> quantity = myadapter.Quantity;
                Intent intent = new Intent(CartActivity.this, Wallet.class);
                intent.putExtra("ItemName", items);
                intent.putExtra("totalString", total);
                intent.putExtra("Quantity", quantity);
                startActivity(intent);
            }
        });


    }
}
