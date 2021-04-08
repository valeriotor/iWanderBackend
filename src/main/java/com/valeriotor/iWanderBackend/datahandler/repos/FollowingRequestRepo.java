package com.valeriotor.iWanderBackend.datahandler.repos;

import com.valeriotor.iWanderBackend.model.core.Following;
import com.valeriotor.iWanderBackend.model.core.FollowingRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowingRequestRepo extends JpaRepository<FollowingRequest, FollowingRequest.FollowingRequestPK> {

    List<FollowingRequest> findByTarget_Username(String targetUsername, Pageable pageable);

}
