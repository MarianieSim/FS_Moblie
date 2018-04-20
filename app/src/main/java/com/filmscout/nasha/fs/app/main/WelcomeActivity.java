package com.filmscout.nasha.fs.app.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

<<<<<<< HEAD:app/src/main/java/com/filmscout/nasha/fs/app/main/WelcomeActivity.java
import com.filmscout.nasha.fs.R;
=======
>>>>>>> 0c888c93b6d7875e44963a7f5c8d3c4c12325c98:app/src/main/java/com/example/simeonm/filmscout/WelcomeActivity.java
import com.google.firebase.auth.FirebaseAuth;
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
                startActivity(new Intent(WelcomeActivity.this, MActivity.class));
            }
        });


        Search = (Button) findViewById(R.id.Search);
        Search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
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
            startActivity(new Intent(this, MActivity.class));
        }
    }
}