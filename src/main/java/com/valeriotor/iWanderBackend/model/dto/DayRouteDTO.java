package com.valeriotor.iWanderBackend.model.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.valeriotor.iWanderBackend.model.core.Day;

import javax.persistence.Lob;

public class DayRouteDTO {
    @JsonBackReference
    private DayDTO day;
    private CoordinateDTO[] route;

    public DayRouteDTO() {
    }

    public DayDTO getDay() {
        return day;
    }

    public void setDay(DayDTO day) {
        this.day = day;
    }

    public CoordinateDTO[] getRoute() {
        return route;
    }

    public void setRoute(CoordinateDTO[] route) {
        this.route = route;
    }
}
