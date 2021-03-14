package com.valeriotor.iWanderBackend.model;

import java.time.LocalDate;

public class DateRange {
    private final LocalDate start;
    private final LocalDate end;

    public DateRange(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }
}
