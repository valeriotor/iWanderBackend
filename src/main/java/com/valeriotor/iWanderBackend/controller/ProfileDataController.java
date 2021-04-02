package com.valeriotor.iWanderBackend.controller;

import com.valeriotor.iWanderBackend.datahandler.ProfileDataHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class ProfileDataController {

    private final ProfileDataHandler profileDataHandler;

    @Autowired
    public ProfileDataController(ProfileDataHandler profileDataHandler) {
        this.profileDataHandler = profileDataHandler;
    }

    @PostMapping("/editName")
    public void editName(String newName) {
        profileDataHandler.editName(newName);
    }

    @PostMapping("/editSurname")
    public void editSurname(String newSurname) {
        profileDataHandler.editSurname(newSurname);
    }

}
