package com.eagle.cansacare.booking;

import android.view.View;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eagle.cansacare.R;
import com.google.android.material.button.MaterialButton;

public class TimeSlotViewHolder extends RecyclerView.ViewHolder {

        private final RadioButton timeSlotbtn;

        public TimeSlotViewHolder(@NonNull View itemView) {
            super(itemView);
            timeSlotbtn = itemView.findViewById(R.id.timeSlotbtn);
        }

        void bindData(String slot){
            timeSlotbtn.setText(slot);
        }


}
