package com.filmscout.nasha.fs.app.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.filmscout.nasha.fs.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {
    private Button Register;
    private EditText mEmailView;
    private EditText mRFnameView;
    private EditText mRLnameView;
    private EditText mUsernameView;
    private EditText mPasswordView;
    private EditText mSPasswordView;
    private static final String TAG = "AddToDatabase";
    //add Firebase Database stuff
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mEmailView = (EditText) findViewById(R.id.Remail);
        mRFnameView = (EditText) findViewById(R.id.RFname);
        mRLnameView = (EditText) findViewById(R.id.RLname);
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
        final String Firstname =  mRFnameView.getText().toString();
        final String Lastname =  mRLnameView.getText().toString();
        final String email =  mEmailView.getText().toString().trim();
        final String password = mPasswordView.getText().toString().trim();
        String Spassword = mSPasswordView.getText().toString().trim();

        if (Username.isEmpty()) {
            mUsernameView.setError("Name required");
            mUsernameView.requestFocus();
            return;
        }
        if (Firstname.isEmpty()) {
            mRFnameView.setError("First Name required");
            mRFnameView.requestFocus();
            return;
        }
        if (Lastname.isEmpty()) {
            mRLnameView.setError("Last Name required");
            mRLnameView.requestFocus();
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

                        mFirebaseDatabase = FirebaseDatabase.getInstance();
                        myRef = mFirebaseDatabase.getReference();
                        String userID = user.getUid();

                        myRef.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                // This method is called once with the initial value and again
                                // whenever data at this location is updated.
                                Log.d(TAG, "onDataChange: Added information to database: \n" +
                                        dataSnapshot.getValue());
                            }

                            @Override
                            public void onCancelled(DatabaseError error) {
                                // Failed to read value
                                Log.w(TAG, "Failed to read value.", error.toException());
                            }

                        });

                        final String First =  mRFnameView.getText().toString();
                        final String Last =  mRLnameView.getText().toString();

                        myRef.child("User_ID").child(userID).child("First_Name").setValue(First);
                        myRef.child("User_ID").child(userID).child("Last_Name").setValue(Last);

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

