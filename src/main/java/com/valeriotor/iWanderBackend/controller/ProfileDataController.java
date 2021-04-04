package com.valeriotor.iWanderBackend.controller;

import com.valeriotor.iWanderBackend.auth.ApplicationUserDao;
import com.valeriotor.iWanderBackend.datahandler.ProfileDataHandler;
import com.valeriotor.iWanderBackend.model.dto.UserFrontDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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

    @PutMapping("/setProfileImage")
    public void setProfileImage(@RequestParam MultipartFile image) throws IOException {
        applicationUserDao.setUserProfileImage(image.getBytes());
    }

}
