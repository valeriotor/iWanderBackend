package com.valeriotor.iWanderBackend.datahandler;

import com.valeriotor.iWanderBackend.auth.ApplicationUserDao;
import com.valeriotor.iWanderBackend.model.core.AppUser;
import com.valeriotor.iWanderBackend.model.dto.UserMinimumDTO;
import com.valeriotor.iWanderBackend.util.IntRange;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
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
        Optional<UserMinimumDTO> giuseppide = applicationUserDao.findUsersByPrefix("g", IntRange.of(0, 10)).stream().findFirst();
        assert giuseppide.isPresent();
        assert giuseppide.get().getUsername().equals("Giuseppide");
    }

    @Test
    public void testFindUserPrefix() {
        assert applicationUserDao != null;
        List<UserMinimumDTO> alessandri = applicationUserDao.findUsersByPrefix("Ale", IntRange.of(0, 4));
        List<UserMinimumDTO> valeri = applicationUserDao.findUsersByPrefix("val", IntRange.of(0, 4));
        assert alessandri.size() == 2;
        assert valeri.size() == 1;
    }
}
