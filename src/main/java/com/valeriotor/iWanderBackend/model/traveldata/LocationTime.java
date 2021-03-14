package com.valeriotor.iWanderBackend.model.traveldata;

import java.time.LocalTime;

public class LocationTime {
    private final LocalTime timeStamp;
    private final double latitude;
    private final double longitude;
    private final String name;
    private final String nameId;

    public LocationTime(LocalTime timeStamp, double latitude, double longitude, String name, String nameId) {
        this.timeStamp = timeStamp;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.nameId = nameId;
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
}
