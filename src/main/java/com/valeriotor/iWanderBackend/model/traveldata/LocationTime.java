package com.valeriotor.iWanderBackend.model.traveldata;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalTime;
@Entity
public class LocationTime {

    @Id
    private final long locationTimeId;
    private final LocalTime timeStamp;
    private final double latitude;
    private final double longitude;
    private final String name;
    private final String nameId;
    @ManyToOne(optional = false)
    @JoinColumn(name = "day_id")
    @JsonBackReference
    private final Day day;

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

    public Day getDay() {
        return day;
    }

    public long getLocationTimeId() {
        return locationTimeId;
    }

    public LocationTime withDayId() {
        return new LocationTime(locationTimeId, timeStamp, latitude, longitude, name, nameId, day);
    }

    @Override
    public String toString() {
        return "LocationTime{" +
                "locationTimeId=" + locationTimeId +
                ", date=" + day.getDate() +
                ", timeStamp=" + timeStamp +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", name='" + name + '\'' +
                ", nameId='" + nameId + '\'' +
                '}';
    }
}
