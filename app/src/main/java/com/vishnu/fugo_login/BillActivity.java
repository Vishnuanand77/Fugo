package com.vishnu.fugo_login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class BillActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    TextView paidamt, itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        paidamt = findViewById(R.id.paidamt);
        recyclerView = findViewById(R.id.billview);
//        itemList = findViewById(R.id.itemList);

        Intent bill = getIntent();
        bill.getExtras();

        ArrayList<String> itemNames = (ArrayList<String>) getIntent().getSerializableExtra("ItemNames");
        String billValue = getIntent().getStringExtra("bill");
        ArrayList<String> quantity = (ArrayList<String>) getIntent().getSerializableExtra("Quantity");

        paidamt.setText("Paid amount: "+ billValue);
//        itemList.setText(itemNames.toString());

        recyclerView.setLayoutManager(new LinearLayoutManager(BillActivity.this));
        final BillViewAdapter myadapter = new BillViewAdapter(BillActivity.this, itemNames, quantity);
        recyclerView.setAdapter(myadapter);
    }
}
