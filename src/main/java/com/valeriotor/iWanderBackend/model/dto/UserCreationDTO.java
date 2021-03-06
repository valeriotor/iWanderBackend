package com.valeriotor.iWanderBackend.model.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserCreationDTO {
    private String username;
    private String password;
    private String name;
    private String surname;
    private LocalDate birthDate;
    private String bio;
    private int preferences;
    private String email;


    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public UserCreationDTO() {
        username = "";
        password = "";
        name = "";
        surname = "";
        birthDate = null;
        preferences = 0;
        bio = "";
        email = "";
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

    public int getPreferences() {
        return preferences;
    }

    public void setPreferences(int preferences) {
        this.preferences = preferences;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //public int[] getPreferences() {
    //    return preferences;
    //}
//
    //public void setPreferences(int[] preferences) {
    //    this.preferences = preferences;
    //}
}
