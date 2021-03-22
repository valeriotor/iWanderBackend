package com.valeriotor.iWanderBackend.auth;

import java.util.List;
import java.util.Optional;

public interface ApplicationUserDao {

    Optional<ApplicationUserDetails> findUserDetailsByUsername(String username);
    void addUserDetails(ApplicationUserDetails userDetails);
    default void addUserDetails(List<ApplicationUserDetails> userDetailsList) {
        for(ApplicationUserDetails userDetails : userDetailsList)
            addUserDetails(userDetails);
    }

}
