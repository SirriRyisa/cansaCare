package com.eagle.cansacare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.material.button.MaterialButton;

public class ProfileSetup extends AppCompatActivity {

    private int CurrentProgress = 0;
    private ProgressBar progressBar;
    private MaterialButton startProgress;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setup);

        progressBar= findViewById(R.id.progressBar);
        startProgress = findViewById(R.id.next_progress_btn);


        startProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CurrentProgress = CurrentProgress + 20;
                progressBar.setProgress(CurrentProgress);
                progressBar.setMax(100);

                Intent previousScreen = new Intent(ProfileSetup.this, HomeActivity2.class);
                startActivity(previousScreen);


            }
        });




    }
}