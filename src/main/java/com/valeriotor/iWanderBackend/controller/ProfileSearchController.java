package com.valeriotor.iWanderBackend.controller;

import com.valeriotor.iWanderBackend.datahandler.ProfileDataHandler;
import com.valeriotor.iWanderBackend.model.userdata.Profile;
import com.valeriotor.iWanderBackend.model.userdata.Profile.ProfileRedux;
import com.valeriotor.iWanderBackend.util.IntRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProfileSearchController {

    @Autowired
    private ProfileDataHandler profileDataHandler;

    @RequestMapping("/findUser")
    public List<ProfileRedux> getProfilesByPrefix(String prefix, int start, int end) {
        List<ProfileRedux> profiles = profileDataHandler
                .findUsersByPrefix(prefix, IntRange.of(start, end))
                .stream()
                .map(Profile::redux)
                .collect(Collectors.toList());
        return profiles;
    }
}
