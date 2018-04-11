package com.example.simeonm.filmscout;


import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;


import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class RegisterActivity extends AppCompatActivity {
    private Button Register;
    private EditText mEmailView;
    private EditText mUsernameView;
    private EditText mPasswordView;
    private EditText mSPasswordView;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mEmailView = (EditText) findViewById(R.id.Remail);
        mUsernameView = (EditText) findViewById(R.id.Rname);
        mSPasswordView = (EditText) findViewById(R.id.RSpassword);
        mAuth = FirebaseAuth.getInstance();

        mPasswordView = (EditText) findViewById(R.id.Rpassword);

        Register = (Button) findViewById(R.id.Rbutton);
        Register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                registerUser();
            }
        });


    }

    private void registerUser() {
        final String Username =  mUsernameView.getText().toString();

        final String email =  mEmailView.getText().toString().trim();
        final String password = mPasswordView.getText().toString().trim();
        String Spassword = mSPasswordView.getText().toString().trim();

        if (Username.isEmpty()) {
            mUsernameView.setError("Name required");
            mUsernameView.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            mEmailView.setError("Email is required");
            mEmailView.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmailView.setError("Please enter a valid email");
            mEmailView.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            mPasswordView.setError("Password is required");
            mPasswordView.requestFocus();
            return;
        }

        if (password.length() < 6) {
            mPasswordView.setError("Minimum lenght of password should be 6");
            mPasswordView.requestFocus();
            return;
        }
        if (!(password.equals(Spassword))) {
            mSPasswordView.setError("Password doesn't match");
            mSPasswordView.requestFocus();
            return;
        }




        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            String e = email;
            String p = password;
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {


                    mAuth.signInWithEmailAndPassword(e, p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                            } else {
                                Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    FirebaseUser user = mAuth.getCurrentUser();
                    user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(RegisterActivity.this, "Verification Email Sent", Toast.LENGTH_SHORT).show();
                        }
                    });

                    if (user != null) {
                        UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder()
                                .setDisplayName(Username)
                                .build();

                        user.updateProfile(profile);
                    }
                    finish();
                    startActivity(new Intent(RegisterActivity.this, WelcomeActivity.class));
                } else {

                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(getApplicationContext(), "You are already registered", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

    }
}
