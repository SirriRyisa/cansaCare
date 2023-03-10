package com.eagle.cansacare;

import androidx.appcompat.app.AppCompatActivity;


import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;


public class Services extends AppCompatActivity {

    private TextView services, appointments;

    private int selectedTabNumber = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_booking);

        services = findViewById(R.id.services_tab);
        appointments= findViewById(R.id.appointments_tab);

        getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragmentContainer,ServicesFragment.class, null)
                        .commit();

       services.setOnClickListener(v -> selectTab(1));


       appointments.setOnClickListener(v -> selectTab(2));


//
//        SearchView searchView = findViewById(R.id.search_bar);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                // Handle search query submission here
//                return true;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                // Handle search query text changes here
//                return true;
//            }
//        });

    }

private void selectTab(int tabNumber ) {
    TextView selectedTextView;
    TextView nonselectedServiceView;
    TextView nonselectedAppointmentView;

    if (tabNumber == 1) {
        selectedTextView = services;

        nonselectedServiceView = appointments;


        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainer, ServicesFragment.class, null)
                .commit();
    } else {

        selectedTextView = appointments;

        nonselectedServiceView = services;


        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainer, AppointmentFragment.class, null)
                .commit();

    }

    float slideTo = (tabNumber - selectedTabNumber) * selectedTextView.getWidth();

    TranslateAnimation translateAnimation = new TranslateAnimation(0,slideTo,0,0);
    translateAnimation.setDuration(100);

    if(selectedTabNumber == 1){
        services.startAnimation(translateAnimation);
    }else {
        appointments.startAnimation(translateAnimation);
    }
    translateAnimation.setAnimationListener(new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
             selectedTextView.setBackgroundResource(R.drawable.rounded_tab_colour);
             selectedTextView.setTypeface(null, Typeface.BOLD);
             selectedTextView.setTextColor(Color.parseColor("#000439"));


            nonselectedServiceView.setBackgroundColor(getResources().getColor(android.R.color.transparent));
            nonselectedServiceView.setTextColor(Color.parseColor("#ffffff"));
             selectedTextView.setTypeface(null, Typeface.NORMAL);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    });

    selectedTabNumber = tabNumber;

}

}