package com.valeriotor.iWanderBackend.model.userdata;

import java.time.LocalDate;

public class Friend {
    private final long userId;
    private final LocalDate dateAdded;

    private Friend(long userId, LocalDate dateAdded) {
        this.userId = userId;
        this.dateAdded = dateAdded;
    }
}
