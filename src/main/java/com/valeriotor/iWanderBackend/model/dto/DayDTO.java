package com.valeriotor.iWanderBackend.model.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DayDTO {
    private LocalDate date;
    private String cityId;
    @JsonBackReference
    private TravelPlanDTO travelPlan;
    @JsonManagedReference
    private List<LocationTimeDTO> locationTimes;
    @JsonManagedReference
    private DayRouteDTO route;
    @JsonManagedReference
    private List<CommentDTO> comments;

    public DayDTO() {
        locationTimes = new ArrayList<>();
    }

    public DayDTO(LocalDate date, String cityId, TravelPlanDTO travelPlan, List<LocationTimeDTO> locationTimes) {
        this.date = date;
        this.cityId = cityId;
        this.travelPlan = travelPlan;
        this.locationTimes = locationTimes;
        this.comments = new ArrayList<>();
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

    public DayRouteDTO getRoute() {
        return route;
    }

    public void setRoute(DayRouteDTO route) {
        this.route = route;
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }
}
