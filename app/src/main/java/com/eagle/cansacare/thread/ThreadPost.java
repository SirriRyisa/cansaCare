package com.eagle.cansacare.thread;

public class ThreadPost {

    private String postId;
    private String patientName;
    private String contentPost;
    private long postTime;
    private int likes;

    public ThreadPost(String postId, String patientName, String contentPost, long postTime, int likes) {
        this.postId = postId;
        this.patientName = patientName;
        this.contentPost = contentPost;
        this.postTime = postTime;
        this.likes = likes;
    }

    public ThreadPost() {
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String getPostId) {
        this.postId = postId;
    }

    public String getPatientName() {

        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getContentPost() {
        return contentPost;
    }

    public void setContentPost(String contentPost) {
        this.contentPost = contentPost;
    }

    public long getPostTime() {
        return postTime;
    }

    public void setPostTime(long postTime) {
        this.postTime = postTime;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void like() {
        likes++;
    }
}
