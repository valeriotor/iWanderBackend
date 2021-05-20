package com.valeriotor.iWanderBackend.auth;

import com.valeriotor.iWanderBackend.model.core.AppUser;
import com.valeriotor.iWanderBackend.model.dto.ProfileDTO;
import com.valeriotor.iWanderBackend.model.dto.UserCreationDTO;
import com.valeriotor.iWanderBackend.model.dto.UserFrontDTO;
import com.valeriotor.iWanderBackend.model.dto.UserMinimumDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.Optional;

public interface ApplicationUserDao {


    Optional<AppUser> findUserDetailsByUsername(String username);

    Optional<UserFrontDTO> findUserByUsername(String username);

    void addUserDetails(AppUser userDetails);

    void addUserDetails(List<AppUser> userDetailsList);

    Slice<UserFrontDTO> findUsersByPrefix(String prefix, Pageable pageable);

    long getUserCount();

    boolean createUserProfile(UserCreationDTO userCreationDTO);

    boolean setUserProfileImage(byte[] bytes);

    Optional<ProfileDTO> findProfileByUsername(String username);
}
