package com.valeriotor.iWanderBackend.model.dto;

import java.time.LocalDate;

public class ProfileDTO {
    private String username;
    private String name;
    private String surname;
    private LocalDate birthDate;
    private String bio;
    private int preferences;
    private String imgURL;

    public ProfileDTO() {
        username = "";
        name = "";
        surname = "";
        birthDate = null;
        preferences = 0;
        bio = "";
        imgURL = "";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getPreferences() {
        return preferences;
    }

    public void setPreferences(int preferences) {
        this.preferences = preferences;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }
}
