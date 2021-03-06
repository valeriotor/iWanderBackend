package com.valeriotor.iWanderBackend.datahandler.repos;

import com.valeriotor.iWanderBackend.model.core.Following;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowingRepo extends JpaRepository<Following, Following.FollowingPK> {

    List<Following> findByFollowee_Username(String followeeUsername, Pageable pageable);
    List<Following> findByFollower_Username(String followerUsername, Pageable pageable);

}
