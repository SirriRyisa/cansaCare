package com.eagle.cansacare;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AppointmentOpened extends AppCompatActivity {

    ImageView goBackButton;
    Button cancelAppointment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_opened);


        // Retrieve the data passed from the previous activity (AppointmentAdapter)
        Intent intent = getIntent();
        String appointmentDate = intent.getStringExtra("appointmentDate");
        String appointmentTimeView = intent.getStringExtra("appointmentTimeView");
        String appointmentPatientName = getIntent().getStringExtra("appointmentPatientName");
        String appointmentId = getIntent().getStringExtra("appointmentId");

        // Display the retrieved data in AppointmentOpened activity layout

        TextView appointmentDateText = findViewById(R.id.date_sample_appointment_opened);
        TextView appointmentTimeViewText = findViewById(R.id.appointment_time_sample);
        TextView appointmentPatientNameText = findViewById(R.id.patient_name_appointment_opened);

        appointmentDateText.setText(appointmentDate);
        appointmentTimeViewText.setText(appointmentTimeView);
        appointmentPatientNameText.setText(appointmentPatientName);

        goBackButton = findViewById(R.id.back_ic);
        goBackButton.setOnClickListener(v -> {
            Intent goBackActivity = new Intent(this, HomeActivity2.class);
            goBackActivity.putExtra("goToFragmentFeed", true);
            startActivity(goBackActivity);
        });

        // Get a reference to the Firebase database
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        // Get a reference to the child you want to delete
        DatabaseReference childRef = database.getReference().child("appointments").child(appointmentId);

        cancelAppointment = findViewById(R.id.cancel_appointment_opened);

        // Add an event listener for the button click
        cancelAppointment.setOnClickListener(view -> {
            // Call the removeValue() method on the child reference
            childRef.removeValue()
                    .addOnSuccessListener(aVoid -> Log.d(TAG, "Appointment canceled successfully."))
                    .addOnFailureListener(e -> Log.e(TAG, "Error cancelling appointment: ", e));
        });
    }
}