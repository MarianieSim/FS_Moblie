package com.example.simeonm.filmscout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;

public class MActivity extends AppCompatActivity {

    private Button Login;
    private Button Register;
    private Button Help;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m);
        mAuth = FirebaseAuth.getInstance();
        Login = (Button) findViewById(R.id.Login);
        Login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MActivity.this, LoginActivity.class));
            }
        });



        Register = (Button) findViewById(R.id.Register);
        Register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MActivity.this, RegisterActivity.class));
            }
        });

        Help = (Button) findViewById(R.id.Mhelp);
        Help.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MActivity.this, HelpActivity.class));
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(this, WelcomeActivity.class));
        }
    }
}
