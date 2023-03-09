package com.eagle.cansacare;

public class ThreadPost {

    private String postId;
    private static String patientName;
    private static String contentPost;
    private static long postTime;
    private static int likes;

    public ThreadPost(String postId, String patientName, String contentPost, long postTime, int likes) {
        this.postId = postId;
        ThreadPost.patientName = patientName;
        ThreadPost.contentPost = contentPost;
        ThreadPost.postTime = postTime;
        ThreadPost.likes = likes;
    }

    public ThreadPost() {
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String getPostId) {
        this.postId = postId;
    }

    public static String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        ThreadPost.patientName = patientName;
    }

    public static String getContentPost() {
        return contentPost;
    }

    public void setContentPost(String contentPost) {
        ThreadPost.contentPost = contentPost;
    }

    public static long getPostTime() {
        return postTime;
    }

    public void setPostTime(long postTime) {
        ThreadPost.postTime = postTime;
    }

    public static int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        ThreadPost.likes = likes;
    }

    public void like() {
        likes++;
    }
}
