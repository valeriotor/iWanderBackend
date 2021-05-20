package com.valeriotor.iWanderBackend.datahandler;

import com.valeriotor.iWanderBackend.datahandler.repos.UserDetailsRepo;
import com.valeriotor.iWanderBackend.model.core.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ProfileDataHandler {

    private final UserDetailsRepo userDetailsRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ProfileDataHandler(UserDetailsRepo userDetailsRepo, PasswordEncoder passwordEncoder) {
        this.userDetailsRepo = userDetailsRepo;
        this.passwordEncoder = passwordEncoder;
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

    public void editPassword(String newPassword) {
        AppUser principal = AuthUtil.getPrincipal();
        String encodedPassword = passwordEncoder.encode(newPassword);
        userDetailsRepo.setPasswordForUser(principal.getUsername(), encodedPassword);
    }

    public String getEmail() {
        AppUser principal = AuthUtil.getPrincipal();
        return principal.getEmail();
    }
}
