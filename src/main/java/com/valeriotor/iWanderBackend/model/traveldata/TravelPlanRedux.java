package com.valeriotor.iWanderBackend.model.traveldata;

import com.valeriotor.iWanderBackend.model.VisibilityType;

public class TravelPlanRedux {
    private final int id;
    private final String name;
    private final VisibilityType visibility;

    public TravelPlanRedux(int id, String name, VisibilityType visibility) {
        this.id = id;
        this.name = name;
        this.visibility = visibility;
    }

    public TravelPlanRedux(TravelPlan plan) {
        this(plan.getId(), plan.getName(), plan.getVisibility());
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
}
