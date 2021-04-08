package com.valeriotor.iWanderBackend.datahandler;

import com.valeriotor.iWanderBackend.datahandler.repos.FollowingRepo;
import com.valeriotor.iWanderBackend.datahandler.repos.FollowingRequestRepo;
import com.valeriotor.iWanderBackend.datahandler.repos.UserDetailsRepo;
import com.valeriotor.iWanderBackend.model.core.AppUser;
import com.valeriotor.iWanderBackend.model.core.Following;
import com.valeriotor.iWanderBackend.model.core.FollowingRequest;
import com.valeriotor.iWanderBackend.model.dto.UserFrontDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.valeriotor.iWanderBackend.model.core.Following.*;
import static com.valeriotor.iWanderBackend.model.core.FollowingRequest.FollowingRequestPK;

@Service
public class FollowingDataHandler {

    private final UserDetailsRepo userDetailsRepo;
    private final FollowingRepo followingRepo;
    private final FollowingRequestRepo followingRequestRepo;

    @Autowired
    public FollowingDataHandler(UserDetailsRepo userDetailsRepo, FollowingRepo followingRepo, FollowingRequestRepo followingRequestRepo) {
        this.userDetailsRepo = userDetailsRepo;
        this.followingRepo = followingRepo;
        this.followingRequestRepo = followingRequestRepo;
    }

    public List<UserFrontDTO> viewFollowers(Pageable pageable) {
        AppUser user = AuthUtil.getPrincipal();
        List<Following> followings = followingRepo.findByFollowee_Username(user.getUsername(), pageable);
        List<UserFrontDTO> followers = new ArrayList<>();
        for(Following f : followings) {
            followers.add(new UserFrontDTO(f.getFollower()));
        }
        return followers;
    }

    public boolean askToFollow(String targetName) { // TODO add 'public' profiles that don't need confirmation
        Optional<AppUser> targetOptional = userDetailsRepo.findById(targetName);
        if(targetOptional.isEmpty())
            return false;
        AppUser target = targetOptional.get();
        AppUser asker = AuthUtil.getPrincipal();
        String askerName = asker.getUsername();
        if(askerName.equals(targetName))
            return false;
        FollowingRequestPK followingRequestPK = new FollowingRequestPK(askerName, targetName);
        FollowingPK followingPK = new FollowingPK(askerName, targetName);
        if(followingRequestRepo.existsById(followingRequestPK) || followingRepo.existsById(followingPK))
            return false;
        FollowingRequest followingRequest = new FollowingRequest(asker, target, LocalDateTime.now());
        followingRequestRepo.save(followingRequest);
        return true;
    }

    public List<UserFrontDTO> viewFollowRequests(Pageable pageable) {
        AppUser user = AuthUtil.getPrincipal();
        List<FollowingRequest> followingRequests = followingRequestRepo.findByTarget_Username(user.getUsername(), pageable);
        List<UserFrontDTO> askers = new ArrayList<>();
        for(FollowingRequest f : followingRequests) {
            askers.add(new UserFrontDTO(f.getAsker()));
        }
        return askers;
    }

    public boolean decideFollowingRequest(String confirmedAsker, FollowingRequestDecideAction action) {
        Optional<AppUser> askerOptional = userDetailsRepo.findById(confirmedAsker);
        if(askerOptional.isEmpty())
            return false;
        AppUser asker = askerOptional.get();
        AppUser user = AuthUtil.getPrincipal();
        FollowingRequestPK followingRequestPK = new FollowingRequestPK(confirmedAsker, user.getUsername());
        Optional<FollowingRequest> requestOptional = followingRequestRepo.findById(followingRequestPK);
        if(requestOptional.isEmpty())
            return false;
        followingRequestRepo.deleteById(followingRequestPK);
        if(action == FollowingRequestDecideAction.CONFIRM) {
            Following following = new Following(asker, user, LocalDateTime.now());
            followingRepo.save(following);
        }
        return true;
    }

    public enum FollowingRequestDecideAction {
        CONFIRM, DENY;
    }

}
