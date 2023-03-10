package com.eagle.cansacare;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;


public class ServicesFragment extends Fragment {
    RecyclerView recyclerView;
    ServicesAdapter servicesAdapter;

    private final List<ServicesList> servicesLists = new ArrayList<>();
    DatabaseReference database;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database = FirebaseDatabase.getInstance().getReference("doctors");



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_services, container, false);

        recyclerView = view.findViewById(R.id.servicesRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));





        servicesAdapter = new ServicesAdapter(servicesLists);
        recyclerView.setAdapter(servicesAdapter);

        database = FirebaseDatabase.getInstance().getReference("doctors");


        database.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                servicesLists.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ServicesList serviceList = dataSnapshot.getValue(ServicesList.class);
                    servicesLists.add(serviceList);

                }

                servicesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("TAG", "onCancelled", error.toException());
            }
        });

        return view;

    }
}