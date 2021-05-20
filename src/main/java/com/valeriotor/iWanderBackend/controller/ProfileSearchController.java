package com.valeriotor.iWanderBackend.controller;

import com.valeriotor.iWanderBackend.auth.ApplicationUserDao;
import com.valeriotor.iWanderBackend.model.dto.ProfileDTO;
import com.valeriotor.iWanderBackend.model.dto.UserFrontDTO;
import com.valeriotor.iWanderBackend.model.dto.UserMinimumDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ProfileSearchController {

    @Autowired
    private ApplicationUserDao applicationUserDao;

    @GetMapping("/findUser")
    public List<UserFrontDTO> getProfilesByPrefix(String prefix, Pageable pageable) {
        Slice<UserFrontDTO> users = applicationUserDao.findUsersByPrefix(prefix, pageable);
        return users.getContent();
    }

    @GetMapping("/findUserBy")
    public UserFrontDTO getProfileByUsername(String username) {
        Optional<UserFrontDTO> user = applicationUserDao.findUserByUsername(username);
        return user.orElse(null);
    }

    @GetMapping("/findProfileBy")
    public ProfileDTO getFullProfileByUsername(String username) {
        Optional<ProfileDTO> user = applicationUserDao.findProfileByUsername(username);
        return user.orElse(null);
    }
}
