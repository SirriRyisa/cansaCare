package com.eagle.cansacare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.eagle.cansacare.booking.DoctorSchedule;
import com.eagle.cansacare.booking.TimeSlotAdapter;
import com.eagle.cansacare.getStartedAccountsCreation.GetStarted;
import com.eagle.cansacare.getStartedAccountsCreation.Login;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class ServiceBooking extends AppCompatActivity {

//    RecyclerView recyclerView;
    TimeSlotAdapter timeSlotAdapter;

    public TextView DisplayNameHeading;
    public TextView serviceTitle;

    DatabaseReference database;


    String[] days = new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };

    DoctorSchedule doctorSchedule;

//    public  TextView serviceName;
//
//    public  TextView serviceRole;
//
//    private String selectedDay;
//    private String selectedTimeSlot;
    List<String> timeSlots = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_booking);

        database = FirebaseDatabase.getInstance().getReference("doctors");


        RecyclerView recyclerView = findViewById(R.id.timeSlotRecycler);

        timeSlotAdapter = new TimeSlotAdapter(timeSlots);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(timeSlotAdapter);



        database.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                    TimeSlot timeSlots = dataSnapshot.getValue(TimeSlot.class);
//
//
//                }

//                timeSlotAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("TAG", "onCancelled", error.toException());
            }
        });


        DisplayNameHeading = findViewById(R.id.service_heading);
        serviceTitle = findViewById(R.id.heading);


//        Retrieve data from the previous activity (ServiceFragment)
        Intent intent = getIntent();
        String displayName = intent.getStringExtra("DisplayName");
        String title = intent.getStringExtra("title");
        doctorSchedule = (DoctorSchedule) intent.getSerializableExtra("schedule");

//        Display the retrieved data in booking activity layout
        DisplayNameHeading.setText(displayName);
        serviceTitle.setText(title);


//setting a listener to know the specific date and time selected
        CalendarView calendarView = findViewById(R.id.calendar_view);
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
//Getting month,year and date
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, dayOfMonth);

//Getting the specific days of the week
            String day = days[calendar.get(Calendar.DAY_OF_WEEK) - 1];

            showAvailableTimeSlots(day);

        });
//
        MaterialButton bookingbttn  = findViewById(R.id.bookingBtn);
        bookingbttn.setOnClickListener(view -> {
////         Get the current user's Name
//            String userId = Objects.requireNonNull(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid());
//
//            Log.e("USER", userId);
//
////         Generate a new ID for the post
//            String appointmentId = database.push().getKey();
//
////         Get the current date
//            String date = calendar.getText().toString();
//            // Get the current time
//            String time = timeSlots.getText().toString();
////         Get the current doctorName
//            String doctorName = displayName.getText().toString();
////         Get the current userId
////            String userId = postEditText.getText().toString();
//
////         Create a new Post object with the generated ID, current user's ID, post text, and timestamp
//            AppointmentData appointmentData = new AppointmentData(appointmentId, date, time, doctorName);
////        postId, PatientName, postContent, postTime, 0
////         Save the post to the Realtime Database
//            assert appointmentId != null;
//            database.child(appointmentId).setValue(appointmentData);
//
//            Toast.makeText(ServiceBooking.this, "Post published !", Toast.LENGTH_SHORT).show();
//
//
//            //            getting the data for the specific item selected to move to the
//
//
//         Finish the activity and go the activity of confirming the appointment
            Intent book = new Intent(ServiceBooking.this,ConfirmedBooking.class);
//            book.putExtra("DisplayName",servicesList.getDisplayName());
//            book.putExtra("title",servicesList.getTitle());
//            book.putExtra("schedule",servicesList.getSchedule());
            startActivity(book);
//            finish();
//
        });

    }


//    Getting the times slots allocated to the days of the week from firebase
@SuppressLint("NotifyDataSetChanged")
void showAvailableTimeSlots(String day){

        String availableTimes = null;

        if (Objects.equals(day, days[0])){
            availableTimes = doctorSchedule.Sunday;
        }

        if (Objects.equals(day, days[1])){
            availableTimes = doctorSchedule.Monday;
        }

        if (Objects.equals(day, days[2])){
            availableTimes = doctorSchedule.Tuesday;
        }

        if (Objects.equals(day, days[3])){
            availableTimes = doctorSchedule.Wednesday;
        }

        if (Objects.equals(day, days[4])){
            availableTimes = doctorSchedule.Thursday;
        }

        if (Objects.equals(day, days[5])){
            availableTimes = doctorSchedule.Friday;
        }

        if (Objects.equals(day, days[6])){
            availableTimes = doctorSchedule.Saturday;
        }


//if multiple times  are placed on one day separate them and make it a list
        if (availableTimes != null) {
            String[] slots = availableTimes.split(",");

            List<String> slotsAsList = Arrays.asList(slots);

//            clear already existing time slots and put the current time slots
            timeSlots.clear();
            timeSlots.addAll(slotsAsList);
            timeSlotAdapter.notifyDataSetChanged();


//If no time slot is available show a message
        }else {
            Toast.makeText(this, "No slots available", Toast.LENGTH_SHORT).show();
        }
    }



}





