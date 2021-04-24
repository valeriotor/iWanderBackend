package com.valeriotor.iWanderBackend.controller;

import com.valeriotor.iWanderBackend.auth.ApplicationUserDao;
import com.valeriotor.iWanderBackend.datahandler.ProfileDataHandler;
import com.valeriotor.iWanderBackend.model.dto.UserCreationDTO;
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

    @RequestMapping(value = "/editName", method = {RequestMethod.POST, RequestMethod.PUT})
    public void editName(String newName) {
        profileDataHandler.editName(newName);
    }

    @RequestMapping(value = "/editSurname", method = {RequestMethod.POST, RequestMethod.PUT})
    public void editSurname(String newSurname) {
        profileDataHandler.editSurname(newSurname);
    }

    @PostMapping("/createProfile")
    public ErrorResponse createProfile(@RequestBody UserCreationDTO userCreationDTO) {
        boolean success = applicationUserDao.createUserProfile(userCreationDTO);
        return success ? null : new ErrorResponse(Error.USERNAME_ALREADY_TAKEN);
    }

    @RequestMapping(value = "/setProfileImage", method = {RequestMethod.POST, RequestMethod.PUT})
    public void setProfileImage(@RequestParam MultipartFile image) throws IOException {
        applicationUserDao.setUserProfileImage(image.getBytes());
    }

}
