package com.valeriotor.iWanderBackend.model.traveldata;

import com.valeriotor.iWanderBackend.util.IntRange;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Day implements SingleDateObject, Comparable<Day>{
    private final long travelPlanId;
    @Id
    @GeneratedValue
    private final long id;
    private final LocalDate date;
    private final String cityId;

    public Day() {
        this(0, 0, null, null);
    }

    public Day(long travelPlanId, long id, LocalDate date, String cityId) {
        this.id = id;
        this.travelPlanId = travelPlanId;
        this.date = date;
        this.cityId = cityId;
    }

    public Day(TravelPlan plan, long id, LocalDate date, String cityId) {
        this(plan.getId(), id, date, cityId);
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

    public String getCityId() {
        return cityId;
    }

    public long getTravelPlanId() {
        return travelPlanId;
    }

    public Day withTravelId(long travelId) {
        return new Day(travelId, id, date, cityId);
    }

    @Override
    public int compareTo(Day o) {
        return date.compareTo(o.getDate());
    }
}
