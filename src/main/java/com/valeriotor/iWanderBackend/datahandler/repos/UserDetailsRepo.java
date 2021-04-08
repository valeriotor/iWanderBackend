package com.valeriotor.iWanderBackend.datahandler.repos;

import com.valeriotor.iWanderBackend.model.core.AppUser;
import com.valeriotor.iWanderBackend.model.dto.UserFrontDTO;
import com.valeriotor.iWanderBackend.model.dto.UserMinimumDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDetailsRepo extends JpaRepository<AppUser, String> {

    Slice<UserMinimumDTO> findByUsernameStartingWithIgnoreCase(String prefix, Pageable pageable);

    List<UserFrontDTO> findAllByUsernameIn(List<String> usernames, Pageable pageable);

    @Modifying
    @Query("update AppUser details set details.name = :name where details.username = :username")
    int setNameForUser(@Param("username") String username, @Param("name") String name);

    @Modifying
    @Query("update AppUser details set details.surname = :surname where details.username = :username")
    int setSurnameForUser(@Param("username") String username, @Param("surname") String surname);

    @Modifying
    @Query("update AppUser details set details.imageURL = :imageURL where details.username = :username")
    int setImageUrlForUser(@Param("username") String username, @Param("imageURL") String imageURL);

}
