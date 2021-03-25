package com.valeriotor.iWanderBackend.datahandler.repos;

import com.valeriotor.iWanderBackend.model.traveldata.TravelPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TravelPlanRepo extends JpaRepository<TravelPlan, Long> {
    List<TravelPlan> findAllByUserIdIn(List<Long> userId);
    //@Query("SELECT p FROM Travel_plan p JOIN FETCH p.days WHERE p.id = (:id)")
    //Optional<TravelPlan> findByIdAndFetchDaysEagerly(@Param("id") Long id);
}
