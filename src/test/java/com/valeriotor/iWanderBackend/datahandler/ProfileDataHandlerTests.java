package com.valeriotor.iWanderBackend.datahandler;

import com.valeriotor.iWanderBackend.auth.ApplicationUserDao;
import com.valeriotor.iWanderBackend.model.core.AppUser;
import com.valeriotor.iWanderBackend.model.dto.UserMinimumDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;

import java.util.Optional;

@SpringBootTest
public class ProfileDataHandlerTests {


    @Autowired
    private ApplicationUserDao applicationUserDao;

    @Test
    public void testSourceSizeAndAdd() {
        assert applicationUserDao.getUserCount() == 5;
        AppUser user = new AppUser();
        user.setUsername("Giuseppide");
        applicationUserDao.addUserDetails(user);
        Optional<UserMinimumDTO> giuseppide = applicationUserDao.findUsersByPrefix("g", PageRequest.of(0, 10)).stream().findFirst();
        assert giuseppide.isPresent();
        assert giuseppide.get().getUsername().equals("Giuseppide");
    }

    @Test
    public void testFindUserPrefix() {
        assert applicationUserDao != null;
        Slice<UserMinimumDTO> alessandri = applicationUserDao.findUsersByPrefix("Ale", PageRequest.of(0, 4));
        Slice<UserMinimumDTO> valeri = applicationUserDao.findUsersByPrefix("val", PageRequest.of(0, 4));
        assert alessandri.getNumberOfElements() == 2;
        assert valeri.getNumberOfElements() == 1;
    }
}
