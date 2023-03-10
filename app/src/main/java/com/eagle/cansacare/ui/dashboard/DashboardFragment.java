package com.eagle.cansacare.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import com.eagle.cansacare.R;
import com.eagle.cansacare.thread.ThreadDisplay;
public class DashboardFragment extends Fragment {

    CardView mainThread, cervicalCancer,breastCancer,prostateCancer;

    public DashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        mainThread = view.findViewById(R.id.main_thread_card);
        mainThread.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ThreadDisplay.class);
            startActivity(intent);
        });

        return view;
    }

}