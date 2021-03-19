package com.valeriotor.iWanderBackend.model.traveldata;

import com.valeriotor.iWanderBackend.util.IntRange;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Day implements SingleDateObject, Comparable<Day>{
    private final long userId;
    private final long travelPlanId;
    @Id
    @GeneratedValue
    private final long id;
    private final LocalDate date;
    private final String cityID;

    public Day() {
        this(0, 0, 0, null, null);
    }

    public Day(long userId, long travelPlanId, long id, LocalDate date, String cityID) {
        this.userId = userId;
        this.id = id;
        this.travelPlanId = travelPlanId;
        this.date = date;
        this.cityID = cityID;
    }

    private List<LocationTime> getLocationTimes(IntRange range) {
        return null;
    }

    public long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getCityID() {
        return cityID;
    }

    public long getUserId() {
        return userId;
    }

    public long getTravelPlanId() {
        return travelPlanId;
    }

    @Override
    public int compareTo(Day o) {
        return date.compareTo(o.getDate());
    }
}
