package com.eagle.cansacare;

import com.eagle.cansacare.booking.DoctorSchedule;

public class ServicesList {


//creating memory for display name, title and schedule from firebase
    String displayName, title;

    DoctorSchedule schedule;

    public DoctorSchedule getSchedule() {
        return schedule;
    }

    public void setSchedule(DoctorSchedule schedule) {
        this.schedule = schedule;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
