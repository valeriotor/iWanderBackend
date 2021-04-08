package com.valeriotor.iWanderBackend.datahandler.repos;

import com.valeriotor.iWanderBackend.model.core.TravelPlan;
import com.valeriotor.iWanderBackend.model.dto.TravelPlanMinimumDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TravelPlanRepo extends JpaRepository<TravelPlan, Long> {
    List<TravelPlanMinimumDTO> findAllByUser_usernameIn(List<String> user_Username, Pageable pageable);
    //@Query("SELECT p FROM Travel_plan p JOIN FETCH p.days WHERE p.id = (:id)")
    //Optional<TravelPlan> findByIdAndFetchDaysEagerly(@Param("id") Long id);

    @Modifying
    @Query("update TravelPlan plan set plan.name = :name where plan.id = :id")
    int setNameForTravel(@Param("id") Long id, @Param("name") String name);

}
