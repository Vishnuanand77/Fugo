package com.vishnu.fugo_login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    ImageView myImage;
    TextView title, description;

    String data1,data2;
    int Image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        myImage = findViewById(R.id.imageView);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);

        getData();
        setData();

    }

    private void getData(){
        if(getIntent().hasExtra("Image") && getIntent().hasExtra("data1") && getIntent().hasExtra("data2")){
            data1 = getIntent().getStringExtra("data1");
            data2 = getIntent().getStringExtra("data2");
            Image = getIntent().getIntExtra("Image", 1);
        }
        else{
            Toast.makeText(this, "Data Error", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData(){
        title.setText(data1);
        description.setText(data2);
        myImage.setImageResource(Image);
    }
}