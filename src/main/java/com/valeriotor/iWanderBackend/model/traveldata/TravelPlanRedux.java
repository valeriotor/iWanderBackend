package com.valeriotor.iWanderBackend.model.traveldata;

import com.valeriotor.iWanderBackend.model.VisibilityType;

import java.time.LocalDate;

public class TravelPlanRedux {
    private final int id;
    private final String name;
    private final VisibilityType visibility;
    private final LocalDate startDate;

    public TravelPlanRedux(int id, String name, VisibilityType visibility, LocalDate startDate) {
        this.id = id;
        this.name = name;
        this.visibility = visibility;
        this.startDate = startDate;
    }

    public TravelPlanRedux(TravelPlan plan) {
        this(plan.getId(), plan.getName(), plan.getVisibility(), plan.getStartDate());
    }

    public int getId() {
        return id;
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
}
