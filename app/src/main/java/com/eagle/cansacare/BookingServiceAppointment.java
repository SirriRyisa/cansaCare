package com.eagle.cansacare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.eagle.cansacare.booking.DoctorSchedule;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class BookingServiceAppointment extends AppCompatActivity {

    Button bookAnAppointment;
    ImageView sendBack;

    private final CollectionReference databaseReferenceStore = FirebaseFirestore.getInstance().collection("User");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_service_appointment);

        // Send back to home activity
        sendBack = findViewById(R.id.booking_appointment_back_btn);
        sendBack.setOnClickListener(v -> {
            Intent backHomeActivity = new Intent(BookingServiceAppointment.this,HomeActivity2.class);
            startActivity(backHomeActivity);
            finish();
        });

        //Retrieve data from the previous activity (ServiceFragment)
        Intent intent = getIntent();
        String displayName = intent.getStringExtra("DisplayName");
        String title = intent.getStringExtra("title");

        //Display the retrieved data in booking activity layout
        TextView DisplayNameHeading = findViewById(R.id.doctor_name);
        DisplayNameHeading.setText(displayName);

        TextView serviceTitle = findViewById(R.id.doctor_title);
        serviceTitle.setText(title);


        // Get a reference to the appointments node in the Realtime Database
        DatabaseReference appointmentsRef = FirebaseDatabase.getInstance().getReference().child("appointments");


        bookAnAppointment = findViewById(R.id.button_book_an_appointment);
        bookAnAppointment.setOnClickListener(v -> {

            // Generate a new ID for the appointment
            String appointmentId = appointmentsRef.push().getKey();

            // Generate the ID of the User
            String userId = FirebaseAuth.getInstance().getUid();
            Log.e("USER", userId);

            assert userId != null;
            databaseReferenceStore.document(userId).get().addOnCompleteListener(task -> {

                if (!task.isSuccessful()){
                    return;
                }
                String patientName = Objects.requireNonNull(Objects.requireNonNull(task.getResult().getData()).get("displayName")).toString();

            // Get references to the UI elements
            RadioButton firstTime = findViewById(R.id.choice_first_time);
            RadioButton secondTime = findViewById(R.id.choice_second_time);
            RadioButton thirdTime = findViewById(R.id.choice_third_time);
            RadioButton fourthTime = findViewById(R.id.choice_fourth_time);
            RadioButton fifthTime = findViewById(R.id.choice_fifth_time);
            RadioButton sixthTime = findViewById(R.id.choice_sixth_time);

            String time = null;

            if (firstTime.isChecked()) {
                time = firstTime.getText().toString();
            } else if (secondTime.isChecked()) {
                time = secondTime.getText().toString();
            } else if (thirdTime.isChecked()) {
                time = thirdTime.getText().toString();
            } else if (fourthTime.isChecked()) {
                time = fourthTime.getText().toString();
            } else if (fifthTime.isChecked()) {
                time = fifthTime.getText().toString();
            } else if (sixthTime.isChecked()) {
                time = sixthTime.getText().toString();
            } else {
                Toast.makeText(this, "Please select a date", Toast.LENGTH_SHORT).show();
            }


            // Get references to the UI elements
            RadioButton mondayRadioButton = findViewById(R.id.choice_monday);
            RadioButton tuesdayRadioButton = findViewById(R.id.choice_tuesday);
            RadioButton wednesdayRadioButton = findViewById(R.id.choice_wednesday);
            RadioButton thursdayRadioButton = findViewById(R.id.choice_thursday);
            RadioButton fridayRadioButton = findViewById(R.id.choice_friday);
            RadioButton saturdayRadioButton = findViewById(R.id.choice_saturday);

            String date = null;

            if (mondayRadioButton.isChecked()) {
                date = mondayRadioButton.getText().toString();
            } else if (tuesdayRadioButton.isChecked()) {
                date = tuesdayRadioButton.getText().toString();
            } else if (wednesdayRadioButton.isChecked()) {
                date = wednesdayRadioButton.getText().toString();
            } else if (thursdayRadioButton.isChecked()) {
                date = thursdayRadioButton.getText().toString();
            } else if (fridayRadioButton.isChecked()) {
                date = fridayRadioButton.getText().toString();
            } else if (saturdayRadioButton.isChecked()) {
                date = saturdayRadioButton.getText().toString();
            } else {
                Toast.makeText(this, "Please select a date", Toast.LENGTH_SHORT).show();
            }

            // Get references to the UI elements
            RadioButton inHouseAppointment = findViewById(R.id.in_house_appointment);
            RadioButton virtualAppointment = findViewById(R.id.virtual_appointment);

            String type = null;

            if (inHouseAppointment.isChecked()) {
                type = inHouseAppointment.getText().toString();
            } else if (virtualAppointment.isChecked()) {
                type = virtualAppointment.getText().toString();
            } else {
                Toast.makeText(this, "Please select a date", Toast.LENGTH_SHORT).show();
            }
            // Create a new appointment object
            Appointment appointment = new Appointment(appointmentId, type, date, time,userId,patientName);

            // Debug logging: print out appointment data
            Log.d("Appointment", "Type: " + appointment.getType());
            Log.d("Appointment", "Date: " + appointment.getDate());
            Log.d("Appointment", "Time: " + appointment.getTime());

            // Save the appointment to the Realtime Database
            assert appointmentId != null;
            appointmentsRef.child(appointmentId).setValue(appointment);

            // Debug logging: print confirmation message
            Log.d("Appointment", "Appointment saved to database.");

            // Send to the activity of confirming the appointment
            Intent nextActivity = new Intent(BookingServiceAppointment.this, ConfirmAppointment.class);
            nextActivity.putExtra("type", appointment.getType());
            nextActivity.putExtra("date", appointment.getDate());
            nextActivity.putExtra("time", appointment.getTime());
            nextActivity.putExtra("appointmentId", appointment.getAppointmentId());
            nextActivity.putExtra("DisplayName", appointment.getDoctorName());
            intent.getStringExtra("title");
            nextActivity.putExtra("title", title);

            // Start the new activity
            startActivity(nextActivity);
            finish();
        });


    });

}
    public void onRadioButtonClicked(View view) {
    }
}
