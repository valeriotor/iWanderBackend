package com.valeriotor.iWanderBackend.controller;

import com.valeriotor.iWanderBackend.controller.serializable.SerializableDay;
import com.valeriotor.iWanderBackend.model.VisibilityType;
import com.valeriotor.iWanderBackend.model.traveldata.TravelPlan;

import java.time.LocalDate;
import java.util.List;

public class SerializableTravelPlan {

    private final long id;
    private final long userId;
    private final String name;
    private final VisibilityType visibility;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final List<SerializableDay> days;

    public SerializableTravelPlan(long id, long userId, String name, VisibilityType visibility, LocalDate startDate, LocalDate endDate, List<SerializableDay> days) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.visibility = visibility;
        this.startDate = startDate;
        this.endDate = endDate;
        this.days = days;
    }

    public TravelPlan toTravelPlan() {
        return new TravelPlan(userId, id, name, visibility, days);
    }

    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public VisibilityType getVisibility() {
        return visibility;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public List<SerializableDay> getDays() {
        return days;
    }
}
