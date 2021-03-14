package com.valeriotor.iWanderBackend.model.traveldata.temp;

import com.valeriotor.iWanderBackend.model.VisibilityType;
import com.valeriotor.iWanderBackend.model.traveldata.Day;
import com.valeriotor.iWanderBackend.model.traveldata.TravelPlan;

import java.util.List;

public class IdentitylessTravelPlan {
    private final String name;
    private final VisibilityType visibility;
    private final List<Day> days;

    public IdentitylessTravelPlan(String name, VisibilityType visibility, List<Day> days) {
        this.name = name;
        this.visibility = visibility;
        this.days = days;
    }

    public TravelPlan create(int id) {
        return new TravelPlan(id, name, visibility, days);
    }

}
