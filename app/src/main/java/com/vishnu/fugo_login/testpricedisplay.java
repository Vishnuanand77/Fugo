package com.vishnu.fugo_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class testpricedisplay extends AppCompatActivity {

    TextView testView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testpricedisplay);

        testView = findViewById(R.id.textView4);

        Intent intent = getIntent();
        intent.getExtras();
        ArrayList<Integer> items = (ArrayList<Integer>) getIntent().getSerializableExtra("totalString");

//        testView.setText(items.toString());

        int total=0;
        for(int i =0;i<items.size();i++){
            total=total+ items.get(i);
        }

        testView.setText("Your total is: "+total);
    }
}
