package com.valeriotor.iWanderBackend.model.dto;

import com.valeriotor.iWanderBackend.model.core.Day;

import java.time.LocalDate;

public class DayMinimumDTO {
    private final String cityId;
    private final LocalDate date;

    public DayMinimumDTO(Day day) {
        this.cityId = "DummyCity";
        this.date = day.getDate();
    }

    public String getCityId() {
        return cityId;
    }

    public LocalDate getDate() {
        return date;
    }
}
