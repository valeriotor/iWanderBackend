package com.valeriotor.iWanderBackend.datahandler.repos;

import com.valeriotor.iWanderBackend.model.traveldata.Day;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DayRepo extends JpaRepository<Day, Long> {

    List<Day> findAllByTravelPlanId(long travelPlanId);
    void deleteAllByTravelPlanId(long travelPlanId);
}