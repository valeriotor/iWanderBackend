package com.valeriotor.iWanderBackend.controller;

import com.valeriotor.iWanderBackend.auth.ApplicationUserDAO;
import com.valeriotor.iWanderBackend.datahandler.ProfileDataHandler;
import com.valeriotor.iWanderBackend.model.dto.TextDTO;
import com.valeriotor.iWanderBackend.model.dto.UserCreationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/profile")
public class ProfileDataController {

    private final ProfileDataHandler profileDataHandler;
    private final ApplicationUserDAO applicationUserDao;

    @Autowired
    public ProfileDataController(ProfileDataHandler profileDataHandler, ApplicationUserDAO applicationUserDao) {
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

    @RequestMapping(value = "/editEmail", method = {RequestMethod.POST, RequestMethod.PUT})
    public void editEmail(String newEmail) {
        profileDataHandler.editEmail(newEmail);
    }

    @RequestMapping(value = "/editPreferences", method = {RequestMethod.POST, RequestMethod.PUT})
    public void editPreferences(int newPreferences) {
        profileDataHandler.editPreferences(newPreferences);
    }

    @RequestMapping(value = "/editBio", method = {RequestMethod.POST, RequestMethod.PUT})
    public void editBio(@RequestBody TextDTO newBio) {
        profileDataHandler.editBio(newBio.getText());
    }

    @RequestMapping(value = "/editPassword", method = {RequestMethod.POST, RequestMethod.PUT})
    public void editPassword(@RequestBody TextDTO newPassword) {
        profileDataHandler.editPassword(newPassword.getText());
    }

    @GetMapping("/email")
    public TextDTO getEmail() {
        var text = new TextDTO();
        text.setText(profileDataHandler.getEmail());
        return text;
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
