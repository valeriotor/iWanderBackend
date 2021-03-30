package com.valeriotor.iWanderBackend.model.dto;

public class UserMinimumDTO {
    private final String username;

    public UserMinimumDTO(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
