package com.valeriotor.iWanderBackend.controller.serializable;

import com.valeriotor.iWanderBackend.model.traveldata.Day;
import com.valeriotor.iWanderBackend.model.traveldata.LocationTime;
import com.valeriotor.iWanderBackend.model.traveldata.SingleDateObject;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

public class SerializableDay implements SingleDateObject {
    private final long userId;
    private final long travelPlanId;
    private final long id;
    private final LocalDate date;
    private final String cityID;
    private final List<LocationTime> locationTimes;

    public SerializableDay(long userId, long travelPlanId, long id, LocalDate date, String cityID, List<LocationTime> locationTimes) {
        this.userId = userId;
        this.id = id;
        this.travelPlanId = travelPlanId;
        this.date = date;
        this.cityID = cityID;
        this.locationTimes = locationTimes;
    }

    public long getUserId() {
        return userId;
    }

    public long getId() {
        return id;
    }

    public long getTravelPlanId() {
        return travelPlanId;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getCityID() {
        return cityID;
    }

    public List<LocationTime> getLocationTimes() {
        return locationTimes;
    }

    public Stream<LocationTime> getLocationTimesStream() {
        return locationTimes.stream();
    }

    public Day toDay() {
        return new Day(userId, travelPlanId, id, date, cityID);
    }

}
