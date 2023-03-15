package com.eagle.cansacare.booking;

import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eagle.cansacare.R;
import com.google.android.material.button.MaterialButton;

public class TimeSlotViewHolder extends RecyclerView.ViewHolder {

//        private final RadioButton timeSlotbtn;


        RadioButton timeSlotbtn;
        TextView   timeSlottext;

        public TimeSlotViewHolder(@NonNull View itemView) {
            super(itemView);
            timeSlottext = itemView.findViewById(R.id.timeSlottext);
            timeSlotbtn = itemView.findViewById(R.id.timeSlotbtn);
        }

        void bindData(String slot){
            timeSlottext.setText(slot);
        }


}
