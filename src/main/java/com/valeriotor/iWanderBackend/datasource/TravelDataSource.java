package com.valeriotor.iWanderBackend.datasource;

import com.valeriotor.iWanderBackend.model.traveldata.TravelPlan;
import com.valeriotor.iWanderBackend.model.traveldata.TravelPlanRedux;
import com.valeriotor.iWanderBackend.model.traveldata.temp.IdentitylessTravelPlan;
import com.valeriotor.iWanderBackend.util.IntRange;

import java.util.List;
import java.util.Optional;

public interface TravelDataSource {

    TravelPlan addTravel(long userId, IdentitylessTravelPlan plan);

    void updateTravel(long userId, int travelId, TravelPlan travelPlan);

    List<TravelPlanRedux> getTravelsForUser(long userId, IntRange range);

    Optional<TravelPlan> getTravel(long userId, int travelId);

}
