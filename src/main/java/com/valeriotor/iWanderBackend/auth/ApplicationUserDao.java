package com.valeriotor.iWanderBackend.auth;

import com.valeriotor.iWanderBackend.model.core.AppUser;
import com.valeriotor.iWanderBackend.model.dto.UserCreationDTO;
import com.valeriotor.iWanderBackend.model.dto.UserMinimumDTO;
import com.valeriotor.iWanderBackend.util.IntRange;

import java.util.List;
import java.util.Optional;

public interface ApplicationUserDao {


    Optional<AppUser> findUserDetailsByUsername(String username);

    void addUserDetails(AppUser userDetails);

    void addUserDetails(List<AppUser> userDetailsList);

    List<UserMinimumDTO> findUsersByPrefix(String prefix, IntRange range);

    long getUserCount();

    boolean createUserProfile(UserCreationDTO userCreationDTO);

    boolean setUserProfileImage(byte[] bytes);

}
