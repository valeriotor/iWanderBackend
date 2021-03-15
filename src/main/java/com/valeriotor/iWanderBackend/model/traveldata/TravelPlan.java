package com.valeriotor.iWanderBackend.model.traveldata;

import com.google.common.collect.ImmutableList;
import com.valeriotor.iWanderBackend.model.VisibilityType;
import com.valeriotor.iWanderBackend.util.IntRange;

import java.time.LocalDate;
import java.util.List;

public class TravelPlan implements Comparable<TravelPlan>{
    private final int id;
    private final String name;
    private final VisibilityType visibility;
    private final List<Day> days;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public TravelPlan(int id, String name, VisibilityType visibility, List<Day> days) {
        this.id = id;
        this.name = name;
        this.visibility = visibility;
        this.days = ImmutableList.copyOf(days);
        startDate = days.stream().findFirst().map(Day::getDate).orElse(LocalDate.of(1970, 1, 1));
        endDate = days.stream().findFirst().map(Day::getDate).orElse(LocalDate.of(1970, 1, 1));
    }

    private List<Day> getDays(IntRange range) {
        return null;
    }

    private void switchDays(int index1, int index2) {

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public VisibilityType getVisibility() {
        return visibility;
    }

    public List<Day> getDays() {
        return days;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    @Override
    public int compareTo(TravelPlan o) {
        int dateCompare = startDate.compareTo(o.startDate);
        return dateCompare == 0 ? Integer.compare(id, o.getId()) : dateCompare;
    }
}
