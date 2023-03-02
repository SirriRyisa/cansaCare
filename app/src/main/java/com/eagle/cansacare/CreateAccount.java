package com.eagle.cansacare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class CreateAccount extends AppCompatActivity {
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);


        View newAccountBackArrowBtn = findViewById(R.id.back_arrow);
        newAccountBackArrowBtn.setOnClickListener(view -> {
            Intent previousScreen = new Intent(CreateAccount.this, GetStarted.class);
            startActivity(previousScreen);
        });


    }
}