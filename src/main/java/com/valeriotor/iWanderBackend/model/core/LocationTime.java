package com.valeriotor.iWanderBackend.model.core;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class LocationTime {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long locationTimeId;
    private LocalTime timeStamp;
    private double latitude;
    private double longitude;
    private String name;
    private String nameId;
    @ManyToOne(optional = false)
    @JoinColumn(name = "day_id")
    @JsonBackReference
    private Day day;
    @ElementCollection
    private List<String> comments;

    public LocationTime() {
        this(0, null, 0, 0, "", "", null);
    }

    public LocationTime(long locationTimeId, LocalTime timeStamp, double latitude, double longitude, String name, String nameId, Day day) {
        this.locationTimeId = locationTimeId;
        this.day = day;
        this.timeStamp = timeStamp;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.nameId = nameId;
        this.comments = new ArrayList<>();
    }

    public long getLocationTimeId() {
        return locationTimeId;
    }

    public void setLocationTimeId(long locationTimeId) {
        this.locationTimeId = locationTimeId;
    }

    public LocalTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameId() {
        return nameId;
    }

    public void setNameId(String nameId) {
        this.nameId = nameId;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "LocationTime{" +
                "locationTimeId=" + locationTimeId +
                ",dayId=" + day.getId() +
                ",date=" + day.getDate() +
                ",timeStamp=" + timeStamp +
                ",latitude=" + latitude +
                ",longitude=" + longitude +
                ",name='" + name + '\'' +
                ",nameId='" + nameId + '\'' +
                "}";
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }
}
