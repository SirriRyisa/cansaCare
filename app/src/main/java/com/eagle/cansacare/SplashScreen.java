package com.eagle.cansacare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        // Delay the splash screen by 3 seconds
        new Handler().postDelayed((Runnable) () -> {
            // Start the next activity
            Intent displaySplash = new Intent(SplashScreen.this, MainActivity.class);
            startActivity(displaySplash);
            // Close the splash screen activity
            finish();
        }, 3000);
    }
}