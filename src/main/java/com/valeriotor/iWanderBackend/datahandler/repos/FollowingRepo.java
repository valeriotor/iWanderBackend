package com.valeriotor.iWanderBackend.datahandler.repos;

import com.valeriotor.iWanderBackend.model.core.Following;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowingRepo extends JpaRepository<Following, Following.FollowingPK> {



}
