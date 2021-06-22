package com.valeriotor.iWanderBackend.model.core;

import javax.persistence.*;

@Entity
public class DayComment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "day_id")
    private Day day;
    private String location;
    private String text;
    private String imageURL;

    public DayComment() {
        this(0, "", "", "");
    }

    public DayComment(long id, String location, String text, String imageURL) {
        this.id = id;
        this.location = location;
        this.text = text;
        this.imageURL = imageURL;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }
}
