package com.eagle.cansacare.booking;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eagle.cansacare.R;


import java.util.List;

public class TimeSlotAdapter extends RecyclerView.Adapter<TimeSlotViewHolder> {


    private final List<String> timeSlot;


    public TimeSlotAdapter(List<String> timeSlot) {
        this.timeSlot = timeSlot;
    }

    @NonNull
    @Override
    public TimeSlotViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.time_slot_list, parent, false);
        return new TimeSlotViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeSlotViewHolder holder, int position) {
        String slot = timeSlot.get(position);
        holder.bindData(slot);
    }


    @Override
    public int getItemCount() {
        return timeSlot.size();
    }


}
