package com.eagle.cansacare.profileSetUp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;

import com.eagle.cansacare.HomeActivity2;
import com.eagle.cansacare.R;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileSetup extends AppCompatActivity {

    private int CurrentProgress = 0;
    private ProgressBar progressBar;
    private Button startProgress;
    PatientsCategory patientsCategory;
    RadioButton hopeful, fighter, survivor;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setup);

        progressBar= findViewById(R.id.progressBar);
        startProgress = findViewById(R.id.next_progress_btn);
        patientsCategory = new PatientsCategory();
        hopeful = findViewById(R.id.hopefulRadioBtn);
        fighter = findViewById(R.id.fighterRadioBtn);
        survivor = findViewById(R.id.survivorRadioBtn);

        databaseReference = firebaseDatabase.getInstance().getReference().child("UserCategory");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                   i = (int) snapshot.getChildrenCount();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        startProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstCategory = hopeful.getText().toString();
                String secondCategory = fighter.getText().toString();
                String thirdCategory = survivor.getText().toString();

                databaseReference.child(String.valueOf(i + 1)).setValue(patientsCategory);
                if (hopeful.isChecked()) {
                    patientsCategory.setTypeOfPatient(firstCategory);
                    databaseReference.child(String.valueOf(i+1)).setValue(patientsCategory);
                } else if (fighter.isChecked()) {
                    patientsCategory.setTypeOfPatient(secondCategory);
                    databaseReference.child(String.valueOf(i+1)).setValue(patientsCategory);
                }else {
                    patientsCategory.setTypeOfPatient(thirdCategory);
                    databaseReference.child(String.valueOf(i + 1)).setValue(patientsCategory);
                }

                CurrentProgress = CurrentProgress + 20;
                progressBar.setProgress(CurrentProgress);
                progressBar.setMax(100);
                Intent previousScreen = new Intent(ProfileSetup.this, HomeActivity2.class);

                startActivity(previousScreen);

            }
        });

    }
}