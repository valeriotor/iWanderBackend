package com.valeriotor.iWanderBackend.model.traveldata;

import com.google.common.collect.ImmutableList;
import com.valeriotor.iWanderBackend.util.IntRange;

import java.time.LocalDate;
import java.util.List;

public class Day {
    private final int index;
    private final LocalDate date;
    private final City city;
    private final List<LocationTime> destinations;

    public Day(int index, LocalDate date, City city, List<LocationTime> destinations) {
        this.index = index;
        this.date = date;
        this.city = city;
        this.destinations = ImmutableList.copyOf(destinations);
    }

    private List<LocationTime> getLocationTimes(IntRange range) {
        return null;
    }

    public int getIndex() {
        return index;
    }

    public LocalDate getDate() {
        return date;
    }

    public City getCity() {
        return city;
    }

    public List<LocationTime> getDestinations() {
        return destinations;
    }
}
