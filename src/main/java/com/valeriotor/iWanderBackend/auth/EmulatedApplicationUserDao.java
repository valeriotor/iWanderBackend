package com.valeriotor.iWanderBackend.auth;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("emulated")
public class EmulatedApplicationUserDao implements ApplicationUserDao{
    private final PasswordEncoder passwordEncoder;
    private final List<ApplicationUserDetails> userDetailsList;

    public EmulatedApplicationUserDao(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsList = Lists.newArrayList(
                new ApplicationUserDetails("valeriotor", passwordEncoder.encode("password"), ImmutableList.of(), true, true, true, true),
                new ApplicationUserDetails("alessandro", passwordEncoder.encode("password"), ImmutableList.of(), true, true, true, true),
                new ApplicationUserDetails("cristianal", passwordEncoder.encode("password"), ImmutableList.of(), true, true, true, true)

        );
    }

    @Override
    public Optional<ApplicationUserDetails> findUserDetailsByUsername(String username) {
        return userDetailsList.stream().filter(userDetails -> userDetails.getUsername().equals(username))
                .findFirst();
    }

    @Override
    public void addUserDetails(ApplicationUserDetails userDetails) {
        this.userDetailsList.add(userDetails);
    }
}
