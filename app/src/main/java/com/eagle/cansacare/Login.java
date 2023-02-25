package com.eagle.cansacare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_layout);

        View loginBackArrowBtn = findViewById(R.id.back_arrow);
        loginBackArrowBtn.setOnClickListener(view -> {
            Intent previousScreen = new Intent(Login.this, GetStarted.class);
            startActivity(previousScreen);
        });
    }
}