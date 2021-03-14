package com.valeriotor.iWanderBackend.datasource;

import com.valeriotor.iWanderBackend.model.userdata.Profile;
import com.valeriotor.iWanderBackend.util.IntRange;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface ProfileDataSource {

    List<Profile> findUsersByPrefix(String prefix, IntRange range);

    Optional<Profile> findUserById(long id);

    boolean addProfile(Profile profile);

    long getUserCount();
}
