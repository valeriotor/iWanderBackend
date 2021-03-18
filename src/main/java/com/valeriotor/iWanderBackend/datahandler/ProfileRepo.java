package com.valeriotor.iWanderBackend.datahandler;

import com.valeriotor.iWanderBackend.model.userdata.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfileRepo extends JpaRepository<Profile, Long> {

    List<Profile> findByUsernameStartingWithIgnoreCase(String prefix);

}
