package com.vishnu.fugo_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;

import java.net.URI;

public class SettingsPage extends AppCompatActivity {

    GoogleSignInClient mGoogleSignInClient;

    ImageView profileimg;
    TextView username;
    Button signOut;
    String imageuri, profilename;
    Uri img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);

        profileimg = findViewById(R.id.profileimg);

        username = findViewById(R.id.username);
        signOut = findViewById(R.id.signoutbtn);

        Intent intent = getIntent();
        intent.getExtras();
        profilename = intent.getStringExtra("Profile name");
        imageuri = intent.getStringExtra("Profile img uri");
        img = Uri.parse(imageuri);

        username.setText(profilename);
        Picasso.with(this).load(img).into(profileimg);



        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signout();
            }
        });
    }

    private void signout() {

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                    }
                });

        Intent intent = new Intent(SettingsPage.this, Login.class);
        startActivity(intent);
    }
}
