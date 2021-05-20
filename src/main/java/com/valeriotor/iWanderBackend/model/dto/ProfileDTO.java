package com.valeriotor.iWanderBackend.model.dto;

import java.time.LocalDate;

public class ProfileDTO {
    private String username;
    private String name;
    private String surname;
    private LocalDate birthDate;
    private String bio;
    private int preferences;
    private String imageURL;

    public ProfileDTO(String username, String name, String surname, LocalDate birthDate, String bio, int preferences, String imageURL) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.bio = bio;
        this.preferences = preferences;
        this.imageURL = imageURL;
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

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public String toString() {
        return "ProfileDTO{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDate=" + birthDate +
                ", bio='" + bio + '\'' +
                ", preferences=" + preferences +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }
}
