package com.valeriotor.iWanderBackend.controller;

import com.valeriotor.iWanderBackend.datahandler.DataHandlers;
import com.valeriotor.iWanderBackend.model.userdata.Profile;
import com.valeriotor.iWanderBackend.util.GSONUtil;
import com.valeriotor.iWanderBackend.util.IntRange;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProfileSearchController {

    @RequestMapping("/findUser")
    public String getProfilesByPrefix(String prefix, int start, int end) {
        List<Profile.ProfileRedux> profiles = DataHandlers.getProfileDataHandler()
                .findUsersByPrefix(prefix, IntRange.of(start, end))
                .stream()
                .map(Profile::redux)
                .collect(Collectors.toList());
        return GSONUtil.getGson().toJson(profiles);
    }



}
