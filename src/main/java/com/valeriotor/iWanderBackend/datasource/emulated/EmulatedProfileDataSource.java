package com.valeriotor.iWanderBackend.datasource.emulated;

import com.valeriotor.iWanderBackend.datasource.ProfileDataSource;
import com.valeriotor.iWanderBackend.model.userdata.AccountType;
import com.valeriotor.iWanderBackend.model.userdata.Profile;
import com.valeriotor.iWanderBackend.util.IntRange;

import java.util.*;
import java.util.stream.Collectors;

public class EmulatedProfileDataSource implements ProfileDataSource {

    private final Set<Profile> profiles = new TreeSet<>();

    public EmulatedProfileDataSource() {
        Profile p0 = new Profile(0, "Valeriotor", AccountType.APPLE, "valeriotor");
        Profile p1 = new Profile(1, "Alessandrotie", AccountType.APPLE, "alessandrotie");
        Profile p2 = new Profile(2, "Alessandrover", AccountType.APPLE, "alessandrover");
        Profile p3 = new Profile(3, "Cristianalf", AccountType.APPLE, "cristianalf");
        Profile p4 = new Profile(4, "Fabiocur", AccountType.APPLE, "fabiocur");
        profiles.add(p0);
        profiles.add(p1);
        profiles.add(p2);
        profiles.add(p3);
        profiles.add(p4);
    }

    @Override
    public List<Profile> findUsersByPrefix(String prefix, IntRange range) {
        List<Profile> usersWithPrefix = profiles.stream().filter(p -> p.getUsername().toLowerCase().startsWith(prefix.toLowerCase())).collect(Collectors.toCollection(ArrayList::new));
        return range.getSublist(usersWithPrefix);
    }

    @Override
    public Optional<Profile> findUserById(long id) {
        return profiles.stream().filter(p -> p.getUserId() == id).findFirst();
    }

    @Override
    public boolean addProfile(Profile profile) {
        return profiles.add(profile);
    }

    @Override
    public long getUserCount() {
        return profiles.size();
    }
}
