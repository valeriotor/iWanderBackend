package com.valeriotor.iWanderBackend.model.traveldata;

import com.google.common.collect.ImmutableList;
import com.valeriotor.iWanderBackend.model.VisibilityType;
import com.valeriotor.iWanderBackend.util.IntRange;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Entity
public class TravelPlan implements Comparable<TravelPlan>{
    @Id
    @GeneratedValue
    private final long id;
    private final long userId;
    private final String name;
    @Enumerated
    @Column(columnDefinition = "smallint")
    private final VisibilityType visibility;
    private LocalDate startDate;
    @OneToMany(mappedBy = "travelPlan", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<Day> days;

    public TravelPlan() {
        this(0, 0, "", null, LocalDate.now(), new ArrayList<>());
    }

    public TravelPlan(long userId, long id, String name, VisibilityType visibility, LocalDate startDate, List<Day> days) {
        this.userId = userId;
        this.id = id;
        this.name = name;
        this.visibility = visibility;
        this.startDate = startDate;
        this.days = new ArrayList<>(days);
    }

    public TravelPlan(long userId, long id, String name, VisibilityType visibility, List<Day> days) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.visibility = visibility;
        startDate = days.isEmpty() ? LocalDate.of(1970, 1, 1) : days.get(0).getDate();
        this.days = new ArrayList<>(days);
    }

    public List<Day> getDays() {
        return days;
    }

    private void switchDays(int index1, int index2) {

    }

    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
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

    public TravelPlan withName(String newName) {
        return new TravelPlan(userId, id, newName, visibility, startDate, days);
    }

    public void setDays(List<Day> days) {
        this.days = new ArrayList<>(days);
        startDate = days.isEmpty() ? LocalDate.of(1970, 1, 1) : days.get(0).getDate();
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Override
    public int compareTo(TravelPlan o) {
        int dateCompare = startDate.compareTo(o.startDate);
        return dateCompare == 0 ? Long.compare(id, o.getId()) : dateCompare;
    }
}
