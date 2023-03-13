package com.eagle.cansacare.booking;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eagle.cansacare.R;


import java.util.List;

public class TimeSlotAdapter extends RecyclerView.Adapter<TimeSlotViewHolder> {


    private final List<String> timeSlot;
    private  static RadioButton lastChecked = null;
    private static int lastCheckedPos= -1;

    private boolean isSelected;



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
//        holder.slot.setText(timeSlot[position]);
//        holder.timeSlotbtn.setChecked(slot.isSelected());
//        holder.timeSlotbtn.setTag(new Integer(position));
        holder.bindData(slot);

//        if(position == 0 && slot.isSelected()) {
//            lastChecked = holder.timeSlotbtn;
//            lastCheckedPos = 0;
//        }

//        holder.timeSlotbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                RadioButton radioButton = (RadioButton) v;
//                int clickedPos = ((Integer) radioButton.getTag()).intValue();
//
//                if(radioButton.isChecked()){
//                    if(lastChecked != null){
//                        lastChecked.setChecked(false);
//                        timeSlot.get(lastCheckedPos).setSelected(false);
//                    }
//                    lastChecked= radioButton;
//                    lastCheckedPos = clickedPos;
//                }else {
//                    lastChecked = null;
//                }
//                timeSlot.get(clickedPos).setSelected(radioButton.isChecked());
//            }
//        });
    }


    @Override
    public int getItemCount() {
        return timeSlot.size();
    }


}
