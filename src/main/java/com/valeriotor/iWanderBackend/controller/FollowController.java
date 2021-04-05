package com.valeriotor.iWanderBackend.controller;

import com.valeriotor.iWanderBackend.datahandler.FollowingDataHandler;
import com.valeriotor.iWanderBackend.model.dto.UserFrontDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.valeriotor.iWanderBackend.datahandler.FollowingDataHandler.*;

@RestController
@RequestMapping("/follow")
public class FollowController {

    private final FollowingDataHandler followingDataHandler;

    @Autowired
    public FollowController(FollowingDataHandler followingDataHandler) {
        this.followingDataHandler = followingDataHandler;
    }

    @PostMapping("/askFollow")
    public void askToFollow(String target) {
        followingDataHandler.askToFollow(target);
    }

    @GetMapping("/viewRequests")
    public List<UserFrontDTO> viewRequests(int start, int end) { // TODO add pagination
        return followingDataHandler.viewFollowRequests();
    }

    @PostMapping("/confirmRequest")
    public void confirmRequest(String askerName) {
        followingDataHandler.decideFollowingRequest(askerName, FollowingRequestDecideAction.CONFIRM);
    }

    @PostMapping("/denyRequest")
    public void denyRequest(String askerName) {
        followingDataHandler.decideFollowingRequest(askerName, FollowingRequestDecideAction.DENY);
    }

}
