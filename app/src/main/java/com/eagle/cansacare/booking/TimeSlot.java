package com.eagle.cansacare.booking;

import com.google.firebase.Timestamp;

public class TimeSlot {
    private String slot;
    private  boolean isSlected;

    public TimeSlot(String slot, boolean isSelected){
        this.slot = slot;
        this.isSlected = isSelected;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public boolean isSlected() {
        return isSlected;
    }

    public void setSlected(boolean slected) {
        isSlected = slected;
    }
}


