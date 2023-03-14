package com.eagle.cansacare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfirmAppointment extends AppCompatActivity {

    Button cancel, confirmBooking;

    DatabaseReference mdatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_appointment);

        //Retrieve data from the previous activity (ServiceFragment)
        Intent nextActivity = getIntent();
        String displayName = nextActivity.getStringExtra("DisplayName");
        String title = nextActivity.getStringExtra("title");
        String date = nextActivity.getStringExtra("date");
        String time = nextActivity.getStringExtra("time");
        String type = nextActivity.getStringExtra("type");
        String appointmentId = nextActivity.getStringExtra("appointmentId");



        //Display the retrieved data in booking activity layout
        TextView doctorName = findViewById(R.id.doctor_name);
        doctorName.setText(displayName);

        TextView serviceTitle = findViewById(R.id.doctor_speciality);
        serviceTitle.setText(title);

        TextView appointmentDate = findViewById(R.id.appointment_date_selected);
        appointmentDate.setText(date);

        TextView appointmentTime = findViewById(R.id.appointment_time_selected);
        appointmentTime.setText(time);

        cancel = findViewById(R.id.cancel_appointment);
        cancel.setOnClickListener(v -> {
            // Remove the appointment from the database
            DatabaseReference appointmentsRef = FirebaseDatabase.getInstance().getReference("appointments");
            appointmentsRef.child(appointmentId).removeValue();

            // Show a toast message to confirm deletion
            Toast.makeText(getApplicationContext(), "Appointment cancelled", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(ConfirmAppointment.this,HomeActivity2.class);
            startActivity(intent);
            finish();

        });
        confirmBooking = findViewById(R.id.confirm_appointment);
        confirmBooking.setOnClickListener(v -> {
            Intent intent = new Intent(ConfirmAppointment.this,HomeActivity2.class);
            startActivity(intent);
            finish();
        });



    }
}