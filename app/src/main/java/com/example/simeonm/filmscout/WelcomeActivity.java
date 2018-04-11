package com.example.simeonm.filmscout;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

public class WelcomeActivity extends AppCompatActivity {


    private Button Search;
    private Button My_Account;

    private Button Logout;
    private Button Help;

    private Button About_Us;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = mAuth.getCurrentUser();
        My_Account = (Button) findViewById(R.id.MyAccount);
        My_Account.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this, MyAccountActivity.class));
            }
        });

        Logout = (Button) findViewById(R.id.Wlogout);
        Logout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
            }
        });



        Help = (Button) findViewById(R.id.LHelp);
        Help.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this, HelpActivity.class));
            }
        });

        About_Us = (Button) findViewById(R.id.AboutUs);
        About_Us.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this, AboutUsActivity.class));
            }
        });





    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}
