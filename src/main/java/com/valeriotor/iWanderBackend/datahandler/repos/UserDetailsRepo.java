package com.valeriotor.iWanderBackend.datahandler.repos;

import com.valeriotor.iWanderBackend.auth.ApplicationUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepo extends JpaRepository<ApplicationUserDetails, String> {
}
