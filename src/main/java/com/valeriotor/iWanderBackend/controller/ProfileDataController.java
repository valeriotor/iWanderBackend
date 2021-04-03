package com.valeriotor.iWanderBackend.controller;

import com.valeriotor.iWanderBackend.auth.ApplicationUserDao;
import com.valeriotor.iWanderBackend.datahandler.ProfileDataHandler;
import com.valeriotor.iWanderBackend.model.dto.UserFrontDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class ProfileDataController {

    private final ProfileDataHandler profileDataHandler;
    private final ApplicationUserDao applicationUserDao;

    @Autowired
    public ProfileDataController(ProfileDataHandler profileDataHandler, ApplicationUserDao applicationUserDao) {
        this.profileDataHandler = profileDataHandler;
        this.applicationUserDao = applicationUserDao;
    }

    @PostMapping("/editName")
    public void editName(String newName) {
        profileDataHandler.editName(newName);
    }

    @PostMapping("/editSurname")
    public void editSurname(String newSurname) {
        profileDataHandler.editSurname(newSurname);
    }

    @PostMapping("/createProfile")
    public ErrorResponse createProfile(@RequestBody UserFrontDTO userFrontDTO) {
        boolean success = applicationUserDao.createUserProfile(userFrontDTO);
        return success ? null : new ErrorResponse(Error.USERNAME_ALREADY_TAKEN);
    }

}
