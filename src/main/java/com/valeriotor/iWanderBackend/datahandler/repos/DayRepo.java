package com.valeriotor.iWanderBackend.datahandler.repos;

import com.valeriotor.iWanderBackend.model.core.Day;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DayRepo extends JpaRepository<Day, Long> {
    List<Day> findAllByTravelPlan_Id(long travelPlanId, Pageable pageable);
    List<Day> findAllByTravelPlan_IdOrderByDate(long travelPlanId, Pageable pageable);
}
