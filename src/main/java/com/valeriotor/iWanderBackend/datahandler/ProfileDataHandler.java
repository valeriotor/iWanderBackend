package com.valeriotor.iWanderBackend.datahandler;

import com.google.common.collect.ImmutableList;
import com.valeriotor.iWanderBackend.datasource.ProfileDataSource;
import com.valeriotor.iWanderBackend.datasource.emulated.EmulatedProfileDataSource;
import com.valeriotor.iWanderBackend.model.userdata.Profile;
import com.valeriotor.iWanderBackend.util.IntRange;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

//@Component
public class ProfileDataHandler {

    //@Autowired
    private ProfileDataSource source = new EmulatedProfileDataSource();

    public List<Profile> findUsersByPrefix(String prefix, IntRange range) {
        if(prefix == null || range == null) return ImmutableList.of();
        return source.findUsersByPrefix(prefix, range);
    }

    public boolean addUser(Profile profile) {
        return source.addProfile(profile);
    }

    public long getUserCount() {
        return source.getUserCount();
    }

}
