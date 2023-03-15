package com.eagle.cansacare.booking;

public class TimeSlot {
    private String slot;
    private  boolean isSlected;

    public TimeSlot(String slot){
        this.slot = slot;
        this.isSlected = isSlected;
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


