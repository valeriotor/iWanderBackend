package com.valeriotor.iWanderBackend.controller;

import com.valeriotor.iWanderBackend.datahandler.FollowingDataHandler;
import com.valeriotor.iWanderBackend.model.dto.UserFrontDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.valeriotor.iWanderBackend.datahandler.FollowingDataHandler.FollowingRequestDecideAction;

@RestController
@RequestMapping("/follow")
public class FollowController {

    private final FollowingDataHandler followingDataHandler;

    @Autowired
    public FollowController(FollowingDataHandler followingDataHandler) {
        this.followingDataHandler = followingDataHandler;
    }

    @PostMapping("/askFollow/{target}")
    public void askToFollow(@PathVariable String target) {
        followingDataHandler.askToFollow(target);
    }

    @GetMapping("/viewRequests")
    public List<UserFrontDTO> viewRequests(Pageable pageable) {
        return followingDataHandler.viewFollowRequests(pageable);
    }

    @PostMapping("/confirmRequest/{askerName}")
    public void confirmRequest(@PathVariable String askerName) {
        followingDataHandler.decideFollowingRequest(askerName, FollowingRequestDecideAction.CONFIRM);
    }

    @PostMapping("/denyRequest/{askerName}")
    public void denyRequest(@PathVariable String askerName) {
        followingDataHandler.decideFollowingRequest(askerName, FollowingRequestDecideAction.DENY);
    }

    @GetMapping("/viewFollowers") //TODO: permetti di vedere i follower di altri
    public List<UserFrontDTO> viewFollowers(Pageable pageable) {
        return followingDataHandler.viewFollowers(pageable);
    }

}
