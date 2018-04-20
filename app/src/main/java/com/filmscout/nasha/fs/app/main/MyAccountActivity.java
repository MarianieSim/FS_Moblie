package com.filmscout.nasha.fs.app.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.filmscout.nasha.fs.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MyAccountActivity extends AppCompatActivity {

    private TextView Account;
    private Button Edit_Accout;
    private Button Watch;


    private Button Seen;
    private Button Help;

    private Button Logout;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = mAuth.getCurrentUser();
        Account = (TextView) findViewById(R.id.Acctext);
        Account.setText("My Account: " + user.getDisplayName());
        Edit_Accout = (Button) findViewById(R.id.EditAccount);
        Edit_Accout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MyAccountActivity.this, EditAccountActivity.class));
            }
        });


        Watch = (Button) findViewById(R.id.Watch);
        Watch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MyAccountActivity.this, WatchActivity.class));
            }
        });
        Seen = (Button) findViewById(R.id.Seen);
        Seen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MyAccountActivity.this, SeenActivity.class));
            }
        });



        Help = (Button) findViewById(R.id.MAHelp);
        Help.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MyAccountActivity.this, HelpActivity.class));
            }
        });
        Logout = (Button) findViewById(R.id.Alogout);
        Logout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(MyAccountActivity.this, MainActivity.class));
            }
        });




    }
}

