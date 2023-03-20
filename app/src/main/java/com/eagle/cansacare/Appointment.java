package com.eagle.cansacare;

public class Appointment {

    private String appointmentId;
    private String date;
    private String time;
    private String type;
    private String doctorName;
    private String userId;
    private String doctorId;
    private String patientName;

    public Appointment(String appointmentId, String type, String date, String time, String userId,String patientName) {

        this.appointmentId = appointmentId;
        this.type = type;
        this.date = date;
        this.time = time;
        this.userId = userId;
        this.patientName = patientName;

//        this.doctorId = doctorId;
    }

    public Appointment() {

    }

//    public String getDoctorId() {
//        return doctorId;
//    }
//
//    public void setDoctorId(String doctorId) {
//        this.doctorId = doctorId;
//    }


    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
