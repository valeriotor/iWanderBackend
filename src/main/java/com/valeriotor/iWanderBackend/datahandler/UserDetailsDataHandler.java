package com.valeriotor.iWanderBackend.datahandler;

import com.valeriotor.iWanderBackend.auth.ApplicationUserDao;
import com.valeriotor.iWanderBackend.datahandler.images.ImageLocationRepoHandler;
import com.valeriotor.iWanderBackend.datahandler.images.ImageLocationDAO;
import com.valeriotor.iWanderBackend.model.core.ApplicationUserDetails;
import com.valeriotor.iWanderBackend.datahandler.repos.UserDetailsRepo;
import com.valeriotor.iWanderBackend.model.dto.UserFrontDTO;
import com.valeriotor.iWanderBackend.model.dto.UserMinimumDTO;
import com.valeriotor.iWanderBackend.util.IntRange;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository("datahandler")
public class UserDetailsDataHandler implements ApplicationUserDao {

    private final Mapper mapper;
    private final UserDetailsRepo userDetailsRepo;
    private final PasswordEncoder passwordEncoder;
    private final ImageLocationDAO imageLocationDAO;

    @Autowired
    public UserDetailsDataHandler(Mapper mapper, UserDetailsRepo userDetailsRepo, PasswordEncoder passwordEncoder, @Qualifier("imagehandler") ImageLocationRepoHandler imageLocationService) {
        this.mapper = mapper;
        this.userDetailsRepo = userDetailsRepo;
        this.passwordEncoder = passwordEncoder;
        this.imageLocationDAO = imageLocationService;
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

    @Override
    public boolean createUserProfile(UserFrontDTO userFrontDTO) {
        ApplicationUserDetails userDetails = mapper.map(userFrontDTO, ApplicationUserDetails.class);
        userDetails.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        if(userDetailsRepo.existsById(userDetails.getUsername()))
            return false;
        userDetailsRepo.save(userDetails);
        return true;
    }

    @Override
    @Transactional
    public boolean setUserProfileImage(byte[] bytes) {
        ApplicationUserDetails user = AuthUtil.getPrincipal();
        ApplicationUserDetails updatedUser = userDetailsRepo.findById(user.getUsername()).orElse(user);
        String imageUrl = imageLocationDAO.saveImageAndGetURL(bytes, updatedUser);
        userDetailsRepo.setImageUrlForUser(updatedUser.getUsername(), imageUrl);
        userDetailsRepo.flush();
        return true;
    }


}
