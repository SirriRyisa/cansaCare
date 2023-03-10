package com.eagle.cansacare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.radiobutton.MaterialRadioButton;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class ProfileSetup extends AppCompatActivity {

    private int CurrentProgress = 0;
    private ProgressBar progressBar;
    private MaterialButton startProgress;

    private MaterialRadioButton hopefulRadioBtn;
    private MaterialRadioButton fighterRadioBtn;
    private MaterialRadioButton survivorRadioBtn;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setup);

        progressBar= findViewById(R.id.progressBar);
        startProgress = findViewById(R.id.next_progress_btn);
        hopefulRadioBtn = findViewById(R.id.hopefulRadioBtn);
        fighterRadioBtn = findViewById(R.id.fighterRadioBtn);
        survivorRadioBtn = findViewById(R.id.survivorRadioBtn);


        startProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CurrentProgress = CurrentProgress + 20;
                progressBar.setProgress(CurrentProgress);
                progressBar.setMax(100);
                Intent previousScreen = new Intent(ProfileSetup.this, Services.class);

                startActivity(previousScreen);


            }
        });


        hopefulRadioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Uncheck all other buttons except for this one
                fighterRadioBtn.setChecked(false);
                survivorRadioBtn.setChecked(false);
//                fighter = Objects.requireNonNull(FirebaseDatabase.getInstance().getCurrentUser.update);

            }
        });

        fighterRadioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Uncheck all other buttons except for this one
                hopefulRadioBtn.setChecked(false);
                survivorRadioBtn.setChecked(false);
//                fighter = Objects.requireNonNull(FirebaseFirestore.getInstance().getCurrentUser.update)

            }
        });

        survivorRadioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Uncheck all other buttons except for this one
                hopefulRadioBtn.setChecked(false);
                fighterRadioBtn.setChecked(false);
//                fighter = Objects.requireNonNull(FirebaseDatabase.getInstance().getCurrentUser.update)

            }
        });




    }
}