package com.valeriotor.iWanderBackend.datahandler;

import com.valeriotor.iWanderBackend.model.userdata.AccountType;
import com.valeriotor.iWanderBackend.model.userdata.Profile;
import com.valeriotor.iWanderBackend.util.IntRange;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ProfileDataHandlerTests {

    @Autowired
    private ProfileDataHandler profileDataHandler;

    @BeforeEach
    public void fillSource() {

    }

    @Test
    public void testSourceSizeAndAdd() {
        assert profileDataHandler.getUserCount() == 5;
        profileDataHandler.addUser(new Profile(100, "Giuseppide", AccountType.APPLE, "Giuseppido"));
        Optional<Profile> giuseppide = profileDataHandler.findUsersByPrefix("g", IntRange.of(0, 10)).stream().findFirst();
        assert giuseppide.isPresent();
        assert giuseppide.get().getUsername().equals("Giuseppide");
    }

    @Test
    public void testFindUserPrefix() {
        assert profileDataHandler != null;
        List<Profile> alessandri = profileDataHandler.findUsersByPrefix("Ale", IntRange.of(0, 4));
        List<Profile> valeri = profileDataHandler.findUsersByPrefix("val", IntRange.of(0, 4));
        assert alessandri.size() == 2;
        assert valeri.size() == 1;
    }
}
