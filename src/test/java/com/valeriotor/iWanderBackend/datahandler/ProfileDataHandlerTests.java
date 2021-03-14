package com.valeriotor.iWanderBackend.datahandler;

import com.valeriotor.iWanderBackend.model.userdata.Profile;
import com.valeriotor.iWanderBackend.util.IntRange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ProfileDataHandlerTests {

    @BeforeEach
    public void fillSource() {

    }

    @Test
    public void testSourceSize() {
        assert DataHandlers.getProfileDataHandler().getUserCount() == 5;
    }

    @Test
    public void testFindUserPrefix() {
        ProfileDataHandler profileDataHandler = DataHandlers.getProfileDataHandler();
        assert profileDataHandler != null;
        List<Profile> alessandri = profileDataHandler.findUsersByPrefix("Ale", IntRange.of(0, 4));
        List<Profile> valeri = profileDataHandler.findUsersByPrefix("val", IntRange.of(0, 4));
        assert alessandri.size() == 2;
        assert valeri.size() == 1;
    }

}
