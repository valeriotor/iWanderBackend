package com.valeriotor.iWanderBackend.model.core;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class Day implements Comparable<Day>, Cloneable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private LocalDate date;
    private String cityId;
    @ManyToOne(optional = false)
    @JoinColumn(name = "travel_plan_id")
    @JsonBackReference
    private TravelPlan travelPlan;
    @OneToMany(mappedBy = "day", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
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

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public TravelPlan getTravelPlan() {
        return travelPlan;
    }

    public void setTravelPlan(TravelPlan travelPlan) {
        this.travelPlan = travelPlan;
    }

    public List<LocationTime> getLocationTimes() {
        return locationTimes;
    }

    public void setLocationTimes(List<LocationTime> locationTimes) {
        this.locationTimes = locationTimes;
    }

    public void addAllLocationTimes(Collection<LocationTime> locationTimes) {
        this.locationTimes.addAll(locationTimes);
    }

    @Override
    public int compareTo(Day o) {
        return date.compareTo(o.getDate());
    }

    @Override
    public String toString() {
        return "Day{" +
                "id=" + id +
                ",travelId=" + (travelPlan == null ? "none" : travelPlan.getId()) +
                ", date=" + date +
                ", cityId='" + cityId + '\'' +
                ", locationTimes=" + locationTimes +
                '}';
    }
}