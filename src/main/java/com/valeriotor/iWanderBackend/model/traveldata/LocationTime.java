package com.valeriotor.iWanderBackend.model.traveldata;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalTime;
@Entity
public class LocationTime {

    @Id
    private final long locationTimeId;
    private final long dayId;
    private final LocalTime timeStamp;
    private final double latitude;
    private final double longitude;
    private final String name;
    private final String nameId;

    public LocationTime() {
        this(0, 0, null, 0, 0, "", "");
    }

    public LocationTime(long locationTimeId, long dayId, LocalTime timeStamp, double latitude, double longitude, String name, String nameId) {
        this.locationTimeId = locationTimeId;
        this.dayId = dayId;
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

    public long getDayId() {
        return dayId;
    }

    public long getLocationTimeId() {
        return locationTimeId;
    }
}
