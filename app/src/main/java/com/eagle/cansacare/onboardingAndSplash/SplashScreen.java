package com.eagle.cansacare.onboardingAndSplash;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.eagle.cansacare.HomeActivity2;
import com.eagle.cansacare.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

@SuppressLint("CustomSplashScreen")
public class SplashScreen extends AppCompatActivity {


    FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        // Delay the splash screen by 3 seconds
        new Handler().postDelayed(() -> {
            // Start the next activity


//            if a new user opens it they go through the onboarding if the user has an account skip the onboarding
            if (firebaseUser == null){
                Intent displaySplash = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(displaySplash);
            }else {
                Intent displaySplash = new Intent(SplashScreen.this, HomeActivity2.class);
                startActivity(displaySplash);
            }


            // Close the splash screen activity
            finish();
        }, 3000);
    }
}