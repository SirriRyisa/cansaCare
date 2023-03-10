package com.eagle.cansacare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

import com.google.firebase.Timestamp;

public class ServiceBooking extends AppCompatActivity {

    public TextView DisplayNameHeading;
    public TextView serviceTitle;
    private Timestamp timestamp;
    private  boolean done;

    private Long slot;

//generating getters and setter
    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Long getSlot() {
        return slot;
    }

    public void setSlot(Long slot) {
        this.slot = slot;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_booking);



        DisplayNameHeading = findViewById(R.id.service_heading);
        serviceTitle = findViewById(R.id.heading);


//        Retrieve data from the previous activity (PostAdapter)
        Intent intent = getIntent();
        String displayName = intent.getStringExtra("DisplayName");
        String title = intent.getStringExtra("title");

//        Display the retrieved data in booking activity layout
        DisplayNameHeading.setText(displayName);
        serviceTitle.setText(title);


//setting a listener to know the specific date and time selected
        CalendarView calendarView = findViewById(R.id.calendar_view);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                System.out.println("Year = " +year);
                System.out.println("month = " +month);
                System.out.println("day of the moneth = " +dayOfMonth);
            }
        });
    }
}