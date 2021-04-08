package com.valeriotor.iWanderBackend.model.dto;

public class UserMinimumDTO {
    private final String username;
    private final String name;
    private final String surname;

    public UserMinimumDTO(String username, String name, String surname) {
        this.username = username;
        this.name = name;
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}
