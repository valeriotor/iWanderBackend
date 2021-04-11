package com.valeriotor.iWanderBackend.model.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.valeriotor.iWanderBackend.model.VisibilityType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TravelPlanDTO {
    private long id;
    private String name;
    private VisibilityType visibility;
    private LocalDate startDate;
    @JsonManagedReference
    private List<DayDTO> days;

    public TravelPlanDTO() {
        days = new ArrayList<>();
    }

    public TravelPlanDTO(long id, String name, VisibilityType visibility, LocalDate startDate, List<DayDTO> days) {
        this.id = id;
        this.name = name;
        this.visibility = visibility;
        this.startDate = startDate;
        this.days = days;
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

    public List<DayDTO> getDays() {
        return days;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVisibility(VisibilityType visibility) {
        this.visibility = visibility;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setDays(List<DayDTO> days) {
        this.days = days;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
