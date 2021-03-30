package com.valeriotor.iWanderBackend.datahandler.repos;

import com.valeriotor.iWanderBackend.model.core.ApplicationUserDetails;
import com.valeriotor.iWanderBackend.model.dto.UserMinimumDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDetailsRepo extends JpaRepository<ApplicationUserDetails, String> {

    List<UserMinimumDTO> findByUsernameStartingWithIgnoreCase(String prefix);

}
