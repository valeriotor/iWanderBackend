package com.valeriotor.iWanderBackend.datahandler;

import com.valeriotor.iWanderBackend.datasource.TravelDataSource;
import com.valeriotor.iWanderBackend.datasource.emulated.EmulatedTravelDataSource;
import com.valeriotor.iWanderBackend.model.traveldata.TravelPlan;
import com.valeriotor.iWanderBackend.model.traveldata.TravelPlanRedux;
import com.valeriotor.iWanderBackend.model.traveldata.temp.IdentitylessTravelPlan;
import com.valeriotor.iWanderBackend.util.IntRange;

import java.util.*;

public class TravelPlanDataHandler {

    private TravelDataSource travelDataSource = new EmulatedTravelDataSource();

    public TravelPlan addTravel(long userId, IdentitylessTravelPlan plan) {
        return travelDataSource.addTravel(userId, plan);
    }

    public void updateTravel(long userId, TravelPlan travelPlan) {
        travelDataSource.updateTravel(userId, travelPlan.getId(), travelPlan);
    }

    public List<TravelPlanRedux> getTravelsForUser(long userId, IntRange range) {
        return travelDataSource.getTravelsForUser(userId, range);
    }

    public Optional<TravelPlan> getTravel(long userId, int travelId) {
        return travelDataSource.getTravel(userId, travelId);
    }

}
