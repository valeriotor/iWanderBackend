package com.valeriotor.iWanderBackend.model.traveldata;

import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.stream.Collectors;

public class TravelDataContainer {
    private final TravelPlan travelPlan;
    private final List<Day> days;
    private final List<List<LocationTime>> locationTimesPerDay;

    /** days and locationTimesPerDay must have the same size.
     *
     */
    public TravelDataContainer(TravelPlan travelPlan, List<Day> days, List<List<LocationTime>> locationTimesPerDay) {
        if(days.size() != locationTimesPerDay.size()) throw new IllegalArgumentException("days and locationTimesPerDay must have the same size!!");
        this.travelPlan = travelPlan;
        this.days = ImmutableList.copyOf(days);
        this.locationTimesPerDay = ImmutableList.copyOf(locationTimesPerDay);
    }

    public TravelPlan getTravelPlan() {
        return travelPlan;
    }

    public List<Day> getDays() {
        return days;
    }

    public List<List<LocationTime>> getLocationTimesPerDay() {
        return locationTimesPerDay;
    }

    public List<LocationTime> getFlattenedLocationTimesPerDay() {
        return locationTimesPerDay.stream().flatMap(a -> a.stream()).collect(Collectors.toList());
    }
}
