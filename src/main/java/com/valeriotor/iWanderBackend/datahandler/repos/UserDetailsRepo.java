package com.valeriotor.iWanderBackend.datahandler.repos;

import com.valeriotor.iWanderBackend.model.core.ApplicationUserDetails;
import com.valeriotor.iWanderBackend.model.dto.UserMinimumDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDetailsRepo extends JpaRepository<ApplicationUserDetails, String> {

    List<UserMinimumDTO> findByUsernameStartingWithIgnoreCase(String prefix);

    @Modifying
    @Query("update ApplicationUserDetails details set details.name = :name where details.username = :username")
    int setNameForUser(@Param("username") String username, @Param("name") String name);

    @Modifying
    @Query("update ApplicationUserDetails details set details.surname = :surname where details.username = :username")
    int setSurnameForUser(@Param("username") String username, @Param("surname") String surname);


}
