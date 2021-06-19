package com.valeriotor.iWanderBackend.model.core;

import javax.persistence.*;

@Entity
public class DayRoute {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @OneToOne
    private Day day;
    @Lob
    private byte[] route;

    public DayRoute() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public byte[] getRoute() {
        return route;
    }

    public void setRoute(byte[] route) {
        this.route = route;
    }
}
