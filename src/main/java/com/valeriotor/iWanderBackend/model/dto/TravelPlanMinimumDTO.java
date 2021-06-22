package com.valeriotor.iWanderBackend.model.dto;

import com.valeriotor.iWanderBackend.model.VisibilityType;
import com.valeriotor.iWanderBackend.model.core.TravelPlan;

import java.time.LocalDate;

public class TravelPlanMinimumDTO implements Comparable<TravelPlanMinimumDTO>{
    private final long id;
    private final String name;
    private final VisibilityType visibility;
    private final LocalDate startDate;
    private final String mainImageUrl;

    public TravelPlanMinimumDTO(long id, String name, VisibilityType visibility, LocalDate startDate, String mainImageUrl) {
        this.id = id;
        this.name = name;
        this.visibility = visibility;
        this.startDate = startDate;
        this.mainImageUrl = mainImageUrl;
    }

    public TravelPlanMinimumDTO(TravelPlan plan) {
        this(plan.getId(), plan.getName(), plan.getVisibility(), plan.getStartDate(), plan.getMainImageUrl());
    }

    public long getId() {
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

    @Override
    public int compareTo(TravelPlanMinimumDTO o) {
        return startDate.compareTo(o.getStartDate());
    }

    public String getMainImageUrl() {
        return mainImageUrl;
    }

    @Override
    public String toString() {
        return "TravelPlanMinimumDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", visibility=" + visibility +
                ", startDate=" + startDate +
                '}';
    }

}
