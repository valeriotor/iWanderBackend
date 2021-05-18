package com.valeriotor.iWanderBackend.datahandler;

import com.valeriotor.iWanderBackend.datahandler.repos.UserDetailsRepo;
import com.valeriotor.iWanderBackend.model.core.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ProfileDataHandler {

    private final UserDetailsRepo userDetailsRepo;

    @Autowired
    public ProfileDataHandler(UserDetailsRepo userDetailsRepo) {
        this.userDetailsRepo = userDetailsRepo;
    }

    public void editName(String newName) {
        AppUser principal = AuthUtil.getPrincipal();
        userDetailsRepo.setNameForUser(principal.getUsername(), newName);
    }

    public void editSurname(String newSurname) {
        AppUser principal = AuthUtil.getPrincipal();
        userDetailsRepo.setSurnameForUser(principal.getUsername(), newSurname);
    }

    public void editBio(String newBio) {
        AppUser principal = AuthUtil.getPrincipal();
        userDetailsRepo.setBioForUser(principal.getUsername(), newBio);
    }

    public void editEmail(String newEmail) {
        AppUser principal = AuthUtil.getPrincipal();
        userDetailsRepo.setEmailForUser(principal.getUsername(), newEmail);
    }

    public void editPreferences(int newPreferences) {
        AppUser principal = AuthUtil.getPrincipal();
        userDetailsRepo.setPreferencesForUser(principal.getUsername(), newPreferences);
    }

}
