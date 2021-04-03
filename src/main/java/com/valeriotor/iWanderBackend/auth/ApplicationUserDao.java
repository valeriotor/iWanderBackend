package com.valeriotor.iWanderBackend.auth;

import com.valeriotor.iWanderBackend.model.core.ApplicationUserDetails;
import com.valeriotor.iWanderBackend.model.dto.UserFrontDTO;
import com.valeriotor.iWanderBackend.model.dto.UserMinimumDTO;
import com.valeriotor.iWanderBackend.util.IntRange;

import java.util.List;
import java.util.Optional;

public interface ApplicationUserDao {


    Optional<ApplicationUserDetails> findUserDetailsByUsername(String username);

    void addUserDetails(ApplicationUserDetails userDetails);

    void addUserDetails(List<ApplicationUserDetails> userDetailsList);

    List<UserMinimumDTO> findUsersByPrefix(String prefix, IntRange range);

    long getUserCount();

    boolean createUserProfile(UserFrontDTO userFrontDTO);

}
