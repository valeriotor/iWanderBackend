package com.valeriotor.iWanderBackend.model.traveldata;

import com.valeriotor.iWanderBackend.model.VisibilityType;
import com.valeriotor.iWanderBackend.util.IntRange;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
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
    private final LocalDate startDate;
    private final LocalDate endDate;

    public TravelPlan() {
        this(0, 0, "", null, LocalDate.now(), LocalDate.now());
    }

    public TravelPlan(long userId, long id, String name, VisibilityType visibility, LocalDate startDate, LocalDate endDate) {
        this.userId = userId;
        this.id = id;
        this.name = name;
        this.visibility = visibility;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public TravelPlan(long userId, long id, String name, VisibilityType visibility, List<? extends SingleDateObject> days) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.visibility = visibility;
        startDate = days.stream().findFirst().map(SingleDateObject::getDate).orElse(LocalDate.of(1970, 1, 1));
        endDate = days.stream().findFirst().map(SingleDateObject::getDate).orElse(LocalDate.of(1970, 1, 1));
    }

    private List<Day> getDays(IntRange range) {
        return null;
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
        return new TravelPlan(userId, id, newName, visibility, startDate, endDate);
    }

    @Override
    public int compareTo(TravelPlan o) {
        int dateCompare = startDate.compareTo(o.startDate);
        return dateCompare == 0 ? Long.compare(id, o.getId()) : dateCompare;
    }
}
