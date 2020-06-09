package com.vishnu.fugo_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Wallet extends AppCompatActivity {

    private String text = "0";
    private String text2 ;
    public static final String zero = "0";
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";
    public static final String SWITCH1 = "switch1";

    TextView display, bill, billval;
    EditText input;
    Button submit, buy;


    private int value = 0;
    private int value2 = 0;
    private String op = "Data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);

        display = findViewById(R.id.displayamt);
        input = findViewById(R.id.enternum);
        submit = findViewById(R.id.submitbtn);
        buy = findViewById(R.id.buy);
        bill = findViewById(R.id.currentbill);
        billval = findViewById(R.id.billval);



        text2 = "0";

        display.setText("0");

        ArrayList<Integer> itemPrice = (ArrayList<Integer>) getIntent().getSerializableExtra("totalString");
        final ArrayList<String> itemNames = (ArrayList<String>) getIntent().getSerializableExtra("ItemName");
        final ArrayList<String> quantity = (ArrayList<String>) getIntent().getSerializableExtra("Quantity");
        input.setVisibility(View.INVISIBLE);

//        testView.setText(items.toString());

        int total=0;
        for(int i =0;i<itemPrice.size();i++){
            total=total+ itemPrice.get(i);
        }
        billval.setText(""+total);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                input.setVisibility(View.VISIBLE);
                calc();
                saveData();

            }
        });


        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buyfunc(itemNames, quantity);
                saveData();
            }
        });

        loadData();
        updateViews();
    }

        private void buyfunc(ArrayList<String> itemNames, ArrayList<String> quantity) {

            input.setVisibility(View.INVISIBLE);

            Integer useritemval = Integer.parseInt(billval.getText().toString());
            Integer walletvalue = Integer.parseInt(display.getText().toString());

            if(walletvalue<useritemval){
                Toast.makeText(this, "Insufficient funds", Toast.LENGTH_SHORT).show();
            }
            else{
                int result = walletvalue - useritemval;
                display.setText(""+result);
                bill.setVisibility(View.INVISIBLE);
                billval.setVisibility(View.INVISIBLE);
                // Go to another intent.

                Intent token_page = new Intent(Wallet.this, BillActivity.class);
                token_page.putExtra("ItemNames", itemNames);
                token_page.putExtra("bill", ""+useritemval);
                token_page.putExtra("Quantity", quantity);
                startActivity(token_page);
            }
        }

    private void calc() {

        text = input.getText().toString();
        text2 = display.getText().toString();

        if(TextUtils.isEmpty(text2)){
            display.setText(text);
//            buy.setVisibility(View.INVISIBLE);

            return;
        }

        if(TextUtils.isEmpty(text)){
            input.setError("Field empty");
//            buy.setVisibility(View.INVISIBLE);

            return;
        }

        else{
            text = input.getText().toString();
            text2 = display.getText().toString();
            value = Integer.parseInt(text.replaceAll("[\\D]",""));
            value2 = Integer.parseInt(text2.replaceAll("[\\D]",""));
            int res = value + value2;
            text = Integer.toString(res);

            display.setText(text);



        }

    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(TEXT, display.getText().toString());
        editor.apply();

    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        text = sharedPreferences.getString(TEXT, "");
    }

    public void updateViews() {
        display.setText(text);
    }
}
