package com.eagle.cansacare.post;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.ServerValue;

import java.util.HashMap;
import java.util.Map;

public class Post {
    private String title;
    private String description;
    private String userId;
    private Object timeStamp;
    private String key;
    private String picture;

    //This method(empty constructor) is required by firebase to marshalled or assemble to create a new instance of this class when firebase is triggered
    public Post() {
    }

    public Post(String title, String description, String userId, String picture) {
        this.title = title;
        this.description = description;
        this.userId = userId;
        this.timeStamp = ServerValue.TIMESTAMP;
        this.picture = picture;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public String getUserId() {

        return userId;
    }

    public void setUserId(String userId) {

        this.userId = userId;
    }

    public Object getTimeStamp() {

        return timeStamp;
    }

    public void setTimeStamp(Object timeStamp) {

        this.timeStamp = timeStamp;
    }

    public String getKey() {
        return key;
    }

    public void setPostKey(String key) {

        this.key = key;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("title", title);
        result.put("description", description);
        result.put("userId", userId);
        result.put("timeStamp", timeStamp);
        result.put("picture", picture);
        return result;
    }

}
