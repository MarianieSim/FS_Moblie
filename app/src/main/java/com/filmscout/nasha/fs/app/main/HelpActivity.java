package com.filmscout.nasha.fs.app.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.filmscout.nasha.fs.R;

public class HelpActivity extends AppCompatActivity {
    private Button About_us;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        About_us = (Button) findViewById(R.id.HAboutMe);
        About_us.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(HelpActivity.this, AboutUsActivity.class));
            }
        });
    }
}
