package com.example.gamersleague.models;

public class Reviews {

    private String comment;
    private String rating;
    private String pushId;

    public Reviews(String comment, String rating, String pushId) {
        this.comment = comment;
        this.rating = rating;
        this.pushId = pushId;
    }
}
