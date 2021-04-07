package com.valeriotor.iWanderBackend.datahandler;

import com.valeriotor.iWanderBackend.model.dto.UserFrontDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FollowingDataHandlerTests {

    private final FollowingDataHandler followingDataHandler;

    @Autowired
    public FollowingDataHandlerTests(FollowingDataHandler followingDataHandler, UserDetailsDataHandler userDetailsDataHandler) {
        this.followingDataHandler = followingDataHandler;
    }

    @Test
    @Order(0)
    @WithUserDetails("valeriotor")
    public void testAskFollow() {
        assert followingDataHandler.askToFollow("alessandrotie");
    }

    @Test
    @Order(1)
    @WithUserDetails("alessandrotie")
    public void testFollowRequestReceived() {
        List<UserFrontDTO> followRequests = followingDataHandler.viewFollowRequests();
        assert followRequests.size() == 1;
        assert followRequests.get(0).getUsername().equals("valeriotor");
    }

    @Test
    @Order(2)
    @WithUserDetails("alessandrotie")
    public void testConfirmFollowRequest() {
        followingDataHandler.decideFollowingRequest("valeriotor", FollowingDataHandler.FollowingRequestDecideAction.CONFIRM);
        assert followingDataHandler.viewFollowRequests().isEmpty();
        List<UserFrontDTO> followers = followingDataHandler.viewFollowers();
        assert followers.size() == 1;
        assert followers.get(0).getUsername().equals("valeriotor");

    }

}
