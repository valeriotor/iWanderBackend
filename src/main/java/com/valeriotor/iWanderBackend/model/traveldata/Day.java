package com.valeriotor.iWanderBackend.model.traveldata;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.valeriotor.iWanderBackend.util.IntRange;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Day implements SingleDateObject, Comparable<Day>, Cloneable{
    @Id
    @GeneratedValue
    private final long id;
    private final LocalDate date;
    private final String cityId;
    @ManyToOne(optional = false)
    @JoinColumn(name = "travel_plan_id")
    @JsonBackReference
    private final TravelPlan travelPlan;
    @OneToMany(mappedBy = "day", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonManagedReference
    private List<LocationTime> locationTimes;

    public Day() {
        this(0, null, null, null, new ArrayList<>());
    }

    public Day(long id, LocalDate date, String cityId, TravelPlan travelPlan, List<LocationTime> locationTimes) {
        this.id = id;
        this.date = date;
        this.cityId = cityId;
        this.travelPlan = travelPlan;
        this.locationTimes = locationTimes;
    }

    public long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getCityId() {
        return cityId;
    }

    public TravelPlan getTravelPlan() {
        return travelPlan;
    }

    public List<LocationTime> getLocationTimes() {
        return locationTimes;
    }

    public void setLocationTimes(List<LocationTime> locationTimes) {
        this.locationTimes = locationTimes;
    }

    /*public Day withTravelId(long travelId) {
        return new Day(travelId, id, date, cityId, travelPlan);
    }*/

    @Override
    public int compareTo(Day o) {
        return date.compareTo(o.getDate());
    }
}
