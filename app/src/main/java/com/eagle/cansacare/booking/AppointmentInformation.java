package com.eagle.cansacare.booking;

public class AppointmentInformation {

    private  String userDisplayName,time,doctorId,ServiceDisplayName,userId,type,apointementType;
    private long slot;

    public String getUserDisplayName() {
        return userDisplayName;
    }

    public void setUserDisplayName(String userDisplayName) {
        this.userDisplayName = userDisplayName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getServiceDisplayName() {
        return ServiceDisplayName;
    }

    public void setServiceDisplayName(String serviceDisplayName) {
        ServiceDisplayName = serviceDisplayName;
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

    public String getApointementType() {
        return apointementType;
    }

    public void setApointementType(String apointementType) {
        this.apointementType = apointementType;
    }

    public long getSlot() {
        return slot;
    }

    public void setSlot(long slot) {
        this.slot = slot;
    }
}
