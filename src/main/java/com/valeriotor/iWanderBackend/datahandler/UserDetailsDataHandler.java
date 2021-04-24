package com.valeriotor.iWanderBackend.datahandler;

import com.valeriotor.iWanderBackend.auth.ApplicationUserDao;
import com.valeriotor.iWanderBackend.datahandler.images.ImageLocationDAO;
import com.valeriotor.iWanderBackend.datahandler.images.ImageLocationRepoHandler;
import com.valeriotor.iWanderBackend.datahandler.repos.UserDetailsRepo;
import com.valeriotor.iWanderBackend.model.core.AppUser;
import com.valeriotor.iWanderBackend.model.dto.UserCreationDTO;
import com.valeriotor.iWanderBackend.model.dto.UserMinimumDTO;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
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
    public Optional<AppUser> findUserDetailsByUsername(String username) {
        return userDetailsRepo.findById(username);
    }

    @Override
    public void addUserDetails(AppUser userDetails) {
        userDetailsRepo.save(userDetails);
    }

    @Override
    public void addUserDetails(List<AppUser> userDetailsList) {
        userDetailsRepo.saveAll(userDetailsList);
    }

    @Override
    public Slice<UserMinimumDTO> findUsersByPrefix(String prefix, Pageable pageable) {
        if(isPageableSortMalicious(pageable)) return new SliceImpl<>(new ArrayList<>());
        return userDetailsRepo.findByUsernameStartingWithIgnoreCase(prefix, pageable);
    }

    @Override
    public long getUserCount() {
        return userDetailsRepo.count();
    }

    @Override
    public boolean createUserProfile(UserCreationDTO userCreationDTO) {
        AppUser userDetails = mapper.map(userCreationDTO, AppUser.class);
        userDetails.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        if(userDetailsRepo.existsById(userDetails.getUsername()))
            return false;
        userDetailsRepo.save(userDetails);
        return true;
    }

    @Override
    @Transactional
    public boolean setUserProfileImage(byte[] bytes) {
        AppUser user = AuthUtil.getPrincipal();
        AppUser updatedUser = userDetailsRepo.findById(user.getUsername()).orElse(user);
        String imageUrl = imageLocationDAO.saveImageAndGetURL(bytes, updatedUser);
        userDetailsRepo.setImageUrlForUser(updatedUser.getUsername(), imageUrl);
        userDetailsRepo.flush();
        return true;
    }

    private boolean isPageableSortMalicious(Pageable pageable) {
        for(Sort.Order order : pageable.getSort().toList()) {
            if(order.getProperty().equals("password"))
                return true;
        }
        return false;
    }

}
