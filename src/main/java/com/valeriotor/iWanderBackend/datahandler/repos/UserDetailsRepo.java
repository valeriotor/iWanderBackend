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
import java.util.Optional;

public interface UserDetailsRepo extends JpaRepository<AppUser, String> {

    Slice<UserMinimumDTO> findByUsernameStartingWithIgnoreCase(String prefix, Pageable pageable);

    List<UserFrontDTO> findAllByUsernameIn(List<String> usernames, Pageable pageable);

    Optional<UserFrontDTO> findByUsernameIgnoreCase(String username);

    @Modifying
    @Query("update AppUser details set details.name = :name where details.username = :username")
    int setNameForUser(@Param("username") String username, @Param("name") String name);

    @Modifying
    @Query("update AppUser details set details.surname = :surname where details.username = :username")
    int setSurnameForUser(@Param("username") String username, @Param("surname") String surname);

    @Modifying
    @Query("update AppUser details set details.bio = :bio where details.username = :username")
    int setBioForUser(@Param("username") String username, @Param("bio") String bio);

    @Modifying
    @Query("update AppUser details set details.email = :email where details.username = :username")
    int setEmailForUser(@Param("username") String username, @Param("email") String email);

    @Modifying
    @Query("update AppUser details set details.preferences = :preferences where details.username = :username")
    int setPreferencesForUser(@Param("username") String username, @Param("preferences") int preferences);

    @Modifying
    @Query("update AppUser details set details.imageURL = :imageURL where details.username = :username")
    int setImageUrlForUser(@Param("username") String username, @Param("imageURL") String imageURL);

}
