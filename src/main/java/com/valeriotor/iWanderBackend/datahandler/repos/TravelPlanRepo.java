package com.valeriotor.iWanderBackend.datahandler.repos;

import com.valeriotor.iWanderBackend.model.traveldata.TravelPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TravelPlanRepo extends JpaRepository<TravelPlan, TravelPlan.TravelPlanID> {
    List<TravelPlan> findAllByUserIdIn(List<Long> userId);
}
