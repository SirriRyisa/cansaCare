package com.eagle.cansacare.getStartedAccountsCreation;

public class UserModel {
    private String firstname, lastname, email, password, userId, displayName;

<<<<<<< HEAD:app/src/main/java/com/eagle/cansacare/UserModel.java
    private boolean fighter, survivor, hopeful;

    public UserModel(){
=======
    public UserModel() {
>>>>>>> 9b3d7534da11640e7096af4b5afbaaa93692461d:app/src/main/java/com/eagle/cansacare/getStartedAccountsCreation/UserModel.java

    }

    public UserModel(String firstname, String lastname, String email, String password, String userId,String displayName) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.userId = userId;
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
