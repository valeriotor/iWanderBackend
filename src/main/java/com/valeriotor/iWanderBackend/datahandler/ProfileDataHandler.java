package com.valeriotor.iWanderBackend.datahandler;

import com.google.common.collect.ImmutableList;
import com.valeriotor.iWanderBackend.model.userdata.Profile;
import com.valeriotor.iWanderBackend.util.IntRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProfileDataHandler {

    @Autowired
    private ProfileRepo profileRepo;

    public List<Profile> findUsersByPrefix(String prefix, IntRange range) {
        if(prefix == null || range == null) return ImmutableList.of();
        List<Profile> profiles = profileRepo.findByUsernameStartingWithIgnoreCase(prefix);
        return range.getSublist(profiles);
    }

    public Profile addUser(Profile profile) {
        return profileRepo.save(profile);
    }

    public long getUserCount() {
        return profileRepo.count();
    }

}
