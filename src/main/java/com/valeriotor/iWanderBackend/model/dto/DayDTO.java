package com.valeriotor.iWanderBackend.model.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.valeriotor.iWanderBackend.model.core.LocationTime;
import com.valeriotor.iWanderBackend.model.core.TravelPlan;

import java.time.LocalDate;
import java.util.List;

public class DayDTO {
    private LocalDate date;
    private String cityId;
    @JsonBackReference
    private TravelPlanDTO travelPlan;
    @JsonManagedReference
    private List<LocationTimeDTO> locationTimes;

    public DayDTO() {
    }

    public DayDTO(LocalDate date, String cityId, TravelPlanDTO travelPlan, List<LocationTimeDTO> locationTimes) {
        this.date = date;
        this.cityId = cityId;
        this.travelPlan = travelPlan;
        this.locationTimes = locationTimes;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getCityId() {
        return cityId;
    }

    public TravelPlanDTO getTravelPlan() {
        return travelPlan;
    }

    public List<LocationTimeDTO> getLocationTimes() {
        return locationTimes;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public void setTravelPlan(TravelPlanDTO travelPlan) {
        this.travelPlan = travelPlan;
    }

    public void setLocationTimes(List<LocationTimeDTO> locationTimes) {
        this.locationTimes = locationTimes;
    }
}