package com.example.gamersleague.models;

public class Reviews {

    private String comment;
    private String rating;
    private String userId;
    private String userName;
    public Reviews() {

    }

    public Reviews(String comment, String rating, String userId, String userName) {
        this.comment = comment;
        this.rating = rating;
        this.userId = userId;
        this.userName = userName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
