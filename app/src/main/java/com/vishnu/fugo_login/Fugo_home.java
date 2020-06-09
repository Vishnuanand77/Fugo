package com.vishnu.fugo_login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Fugo_home extends AppCompatActivity {

    //Initializations :
    RecyclerView recycle;
    TextView text; //Welcome,User! Textbox
    ImageView imageView;//Profile image from the google sign in
    Button settingspage;//Button to go to the settings page

    String personName;//Username taken from the google sign in
    Uri personPhoto;//Uri of the google profile picture

    Button mOrder, mCart;//Buttons of order checkbox and go to cart.

    ArrayList<String> mItemSelected = new ArrayList<>();//Array list of items that is selected by the user
    ArrayList<String> mItemPriceSelected = new ArrayList<>();
    ArrayList<String> mUserItems = new ArrayList<>();//Array list of available items
    ArrayList<String> mUserItemPrice = new ArrayList<>();
    ArrayList<Integer> mUserImage = new ArrayList<>();

    boolean[] checkedItems;

    String Items[], Descriptions[], Prices[];//String arrays

    //Lining the images in order of the Items string array. Ref: strings.xml
    int images[] = {R.drawable.masaladosa, R.drawable.setdosa, R.drawable.openbutter,
            R.drawable.idli, R.drawable.vada, R.drawable.chole,
            R.drawable.vegbiryani, R.drawable.aloo,
            R.drawable.vegfried, R.drawable.coffee, R.drawable.tea};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fugo_home);

        //xml asset declarations:
        recycle = findViewById(R.id.recyclerView);
        text = findViewById(R.id.welcome);
        imageView = findViewById(R.id.user);
        settingspage = findViewById(R.id.Settings);
        mOrder = findViewById(R.id.mOrder);
        mCart = findViewById(R.id.cartbtn);

        Items = getResources().getStringArray(R.array.Items);
        Descriptions = getResources().getStringArray(R.array.Descriptions);
        Prices = getResources().getStringArray(R.array.price);
        checkedItems =new boolean[Items.length];

        //Calling the recycler view via an adapter that handles displaying appropriate data from
        //the string-arrays and displaying it in a card view.
        recycle.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewAdapter myadapter = new RecyclerViewAdapter(this, Items,Descriptions,Prices, images);
        recycle.setAdapter(myadapter);


        //Sign in using Google api and getting username and image uri data.
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            personName = acct.getDisplayName();
            personPhoto = acct.getPhotoUrl();

            text.setText("Welcome, "+ personName);
            Picasso.with(this).load(personPhoto).into(imageView);
        }

        //Button going to settings page. Image uri and username is passed through intents.
        settingspage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingspage = new Intent(Fugo_home.this, SettingsPage.class);
                settingspage.putExtra("Profile name", personName);
                settingspage.putExtra("Profile img uri", personPhoto.toString());
                startActivity(settingspage);
            }
        });

        //OnClick Listener for the Add to cart button. This is a check box list pop-up function
        mOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Fugo_home.this);
                mBuilder.setTitle(R.string.dialog_title);
                mBuilder.setMultiChoiceItems(Items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if(isChecked){
                            if(!mUserItems.contains(which)) {
                                mUserItems.add(Items[which]);
                                mUserItemPrice.add(Prices[which]);
                                mUserImage.add(images[which]);
                            }
                        }
                        else{
                            mUserItems.remove(Items[which]);
                            mUserItemPrice.remove(Prices[which]);
                            mUserImage.remove(Integer.valueOf(images[which]));
                        }
                    }
                });
                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton(R.string.ok_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String item = "";
                        String price = "";
                        for(int i=0 ; i<mUserItems.size() ; i++) {
                            item = item + Items[i];
                            price = price+ Prices[i];
                            mItemSelected.add(item);
                            mItemPriceSelected.add(price);
                        }
                    }
                });

                mBuilder.setNegativeButton(R.string.dismiss_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                mBuilder.setNeutralButton(R.string.clear_all_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for(int i=0 ; i<checkedItems.length ; i++){
                            checkedItems[i]=false;
                            mUserItems.clear();
                            mItemSelected.clear();
                            mItemPriceSelected.clear();
                            mUserItemPrice.clear();
                            mUserImage.clear();
                        }
                    }
                });

                AlertDialog mDialog =mBuilder.create();
                mDialog.show();
            }
        });

        //onClick Listener for the go to cart button. This button would send array list the user chose from the
        //pop up check box list.
        mCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cartIntent = new Intent(Fugo_home.this, CartActivity.class);
                cartIntent.putExtra("Items", mUserItems);
                cartIntent.putExtra("Prices", mUserItemPrice);
                cartIntent.putExtra("Image", mUserImage);
                startActivity(cartIntent);
            }
        });
    }
}
