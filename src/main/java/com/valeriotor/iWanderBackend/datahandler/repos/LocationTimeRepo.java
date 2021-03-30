package com.valeriotor.iWanderBackend.datahandler.repos;

import com.valeriotor.iWanderBackend.model.core.LocationTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationTimeRepo extends JpaRepository<LocationTime, Long> {
    //List<LocationTime> findByDayId(long dayId);
    //void deleteAllByDayIdIn(List<Long> dayIds);
}
