package com.valeriotor.iWanderBackend.model.dto;

import com.valeriotor.iWanderBackend.model.core.Day;

import java.time.LocalDate;

public class DayMinimumDTO {
    private final String cityName;
    private final LocalDate date;

    public DayMinimumDTO(Day day) {
        this.cityName = "DummyCity";
        this.date = day.getDate();
    }

    public String getCityName() {
        return cityName;
    }

    public LocalDate getDate() {
        return date;
    }
}
