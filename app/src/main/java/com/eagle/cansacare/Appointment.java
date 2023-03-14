package com.eagle.cansacare;

public class Appointment {

    private String appointmentId;
    private String date;
    private String time;
    private String type;
    private String doctorName;
    private String userId;

    public Appointment(String appointmentId, String type, String date, String time) {
        this.appointmentId = appointmentId;
        this.type = type;
        this.date = date;
        this.time = time;
    }

    public class appointment {

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
}
