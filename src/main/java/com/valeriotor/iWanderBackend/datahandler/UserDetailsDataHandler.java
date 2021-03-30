package com.valeriotor.iWanderBackend.datahandler;

import com.valeriotor.iWanderBackend.auth.ApplicationUserDao;
import com.valeriotor.iWanderBackend.model.core.ApplicationUserDetails;
import com.valeriotor.iWanderBackend.datahandler.repos.UserDetailsRepo;
import com.valeriotor.iWanderBackend.model.dto.UserMinimumDTO;
import com.valeriotor.iWanderBackend.util.IntRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("datahandler")
public class UserDetailsDataHandler implements ApplicationUserDao {

    private final UserDetailsRepo userDetailsRepo;

    @Autowired
    public UserDetailsDataHandler(UserDetailsRepo userDetailsRepo) {
        this.userDetailsRepo = userDetailsRepo;
    }

    @Override
    public Optional<ApplicationUserDetails> findUserDetailsByUsername(String username) {
        return userDetailsRepo.findById(username);
    }

    @Override
    public void addUserDetails(ApplicationUserDetails userDetails) {
        userDetailsRepo.save(userDetails);
    }

    @Override
    public void addUserDetails(List<ApplicationUserDetails> userDetailsList) {
        userDetailsRepo.saveAll(userDetailsList);
    }

    @Override
    public List<UserMinimumDTO> findUsersByPrefix(String prefix, IntRange range) {
        return range.getSublist(userDetailsRepo.findByUsernameStartingWithIgnoreCase(prefix));
    }

    @Override
    public long getUserCount() {
        return userDetailsRepo.count();
    }
}
