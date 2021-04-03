package com.valeriotor.iWanderBackend.model.dto;

import java.time.LocalDate;

public class UserFrontDTO {
    private String username;
    private String password;
    private String name;
    private String surname;
    private LocalDate birthDate;


    public UserFrontDTO() {
        this.username = "";
        this.password = "";
        this.name = "";
        this.surname = "";
        this.birthDate = null;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
