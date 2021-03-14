package com.valeriotor.iWanderBackend.model.userdata;

import java.time.LocalDate;

public class FriendRequest {
    private final long userId;
    private final LocalDate dateSent;

    private FriendRequest(long userId, LocalDate dateSent) {
        this.userId = userId;
        this.dateSent = dateSent;
    }
}
