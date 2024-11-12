package com.nyinst.farmease.model;

public class FeedbackModel {
    int id;
    String createdBy;
    String name;
    String rating;
    String feedback;

    public FeedbackModel(int id, String createdBy,String name,String rating, String feedback) {
        this.id = id;
        this.createdBy = createdBy;
        this.name = name;
        this.rating = rating;
        this.feedback = feedback;
    }

    public String getCreateBy() {
        return createdBy;
    }

    public String getName() { return name; }
    public void setName(String name) {this.name = name; }

    public String getRating() { return rating; }
    public void setRating(String rating) {this.rating = rating; }

    public String getFeedback() { return feedback; }
    public void setFeedback(String feedback) {this.feedback = feedback; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
