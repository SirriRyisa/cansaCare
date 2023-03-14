package com.eagle.cansacare.ui.bookings;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.eagle.cansacare.AppointmentFragment;
import com.eagle.cansacare.R;
import com.eagle.cansacare.ServicesFragment;
import com.eagle.cansacare.databinding.FragmentNotificationsBinding;
import com.eagle.cansacare.thread.ThreadDisplay;

public class BookingFragment extends Fragment {


    private TextView services, appointments;

    private int selectedTabNumber = 1;

    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_booking, container, false);


        services = view.findViewById(R.id.services_tab);
        appointments = view.findViewById(R.id.appointments_tab);

        getChildFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainer, ServicesFragment.class, null)
                .commit();

        services.setOnClickListener(v -> selectTab(1));


        appointments.setOnClickListener(v -> selectTab(2));
        return view;


    }

    private void selectTab(int tabNumber) {
        TextView selectedTextView;
        TextView nonselectedServiceView;
        TextView nonselectedAppointmentView;

        if (tabNumber == 1) {
            selectedTextView = services;

            nonselectedServiceView = appointments;


            getChildFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragmentContainer, ServicesFragment.class, null)
                    .commit();
        } else {

            selectedTextView = appointments;

            nonselectedServiceView = services;


            getChildFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragmentContainer, AppointmentFragment.class, null)
                    .commit();

        }

        float slideTo = (tabNumber - selectedTabNumber) * selectedTextView.getWidth();

        TranslateAnimation translateAnimation = new TranslateAnimation(0, slideTo, 0, 0);
        translateAnimation.setDuration(100);

        if (selectedTabNumber == 1) {
            services.startAnimation(translateAnimation);
        } else {
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


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}