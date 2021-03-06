package com.valeriotor.iWanderBackend.model.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.valeriotor.iWanderBackend.model.core.DayComment;

public class CommentDTO {
    @JsonBackReference
    private DayDTO day;
    private String location;
    private String text;
    private String imageURL;

    public CommentDTO() {
    }

    public CommentDTO(DayComment dayComment) {
        location = dayComment.getLocation();
        text = dayComment.getText();
        imageURL = dayComment.getImageURL();
    }

    public CommentDTO(String location, String text, String imageURL, DayDTO day) {
        this.location = location;
        this.text = text;
        this.imageURL = imageURL;
        this.day = day;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public DayDTO getDay() {
        return day;
    }

    public void setDay(DayDTO day) {
        this.day = day;
    }
}
