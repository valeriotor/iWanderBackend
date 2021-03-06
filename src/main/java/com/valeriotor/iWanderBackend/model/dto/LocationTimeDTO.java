package com.valeriotor.iWanderBackend.model.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.valeriotor.iWanderBackend.model.core.LocationTime;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class LocationTimeDTO {

    public static LocationTimeDTO convertWithoutDay(LocationTime locationTime) {
        return  new LocationTimeDTO(locationTime.getTimeStamp(), locationTime.getLatitude(), locationTime.getLongitude(), locationTime.getName(), locationTime.getNameId(), null);
    }

    private LocalTime timeStamp;
    private double latitude;
    private double longitude;
    private String name;
    private String nameId;
    @JsonBackReference
    private DayDTO day;

    public LocationTimeDTO() {
    }

    public LocationTimeDTO(LocalTime timeStamp, double latitude, double longitude, String name, String nameId, DayDTO day) {
        this.timeStamp = timeStamp;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.nameId = nameId;
        this.day = day;
    }

    public LocalTime getTimeStamp() {
        return timeStamp;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getName() {
        return name;
    }

    public String getNameId() {
        return nameId;
    }

    public DayDTO getDay() {
        return day;
    }

    public void setTimeStamp(LocalTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNameId(String nameId) {
        this.nameId = nameId;
    }

    public void setDay(DayDTO day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "LocationTimeDTO{" +
                "timeStamp=" + timeStamp +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", name='" + name + '\'' +
                ", nameId='" + nameId + '\'' +
                ", day=" + day +
                '}';
    }
}
