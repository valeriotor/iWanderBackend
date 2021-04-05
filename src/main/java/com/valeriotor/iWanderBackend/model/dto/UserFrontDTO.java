package com.valeriotor.iWanderBackend.model.dto;

import com.valeriotor.iWanderBackend.model.core.AppUser;

public class UserFrontDTO {
    private String username;
    private String name;
    private String surname;
    private String imageURL;

    public UserFrontDTO(String username, String name, String surname, String imageURL) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.imageURL = imageURL;
    }

    public UserFrontDTO(AppUser user) {
        this(user.getUsername(), user.getName(), user.getSurname(), user.getImageURL());
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

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
