package com.valeriotor.iWanderBackend.datahandler;

import com.valeriotor.iWanderBackend.auth.ApplicationUserDAO;
import com.valeriotor.iWanderBackend.model.core.AppUser;
import com.valeriotor.iWanderBackend.model.dto.UserFrontDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.security.test.context.support.WithUserDetails;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ProfileDataHandlerTests {


    @Autowired
    private ApplicationUserDAO applicationUserDao;

    @Test
    public void testSourceSizeAndAdd() {
        assert applicationUserDao.getUserCount() == 5;
        AppUser user = new AppUser();
        user.setUsername("Giuseppide");
        applicationUserDao.addUserDetails(user);
        Optional<UserFrontDTO> giuseppide = applicationUserDao.findUsersByPrefix("g", PageRequest.of(0, 10)).stream().findFirst();
        assertTrue(giuseppide.isPresent());
        assertEquals(giuseppide.get().getUsername(), "Giuseppide");
    }

    @Test
    @WithUserDetails("fabiocur")
    public void testFindUserPrefix() {
        assert applicationUserDao != null;
        Slice<UserFrontDTO> alessandri = applicationUserDao.findUsersByPrefix("Ale", PageRequest.of(0, 4));
        Slice<UserFrontDTO> valeri = applicationUserDao.findUsersByPrefix("val", PageRequest.of(0, 4));
        assertEquals(alessandri.getNumberOfElements(), 2);
        assertEquals(valeri.getNumberOfElements(), 1);
    }
}
