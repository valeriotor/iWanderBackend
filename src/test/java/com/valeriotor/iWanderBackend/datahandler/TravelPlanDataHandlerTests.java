package com.valeriotor.iWanderBackend.datahandler;

import com.valeriotor.iWanderBackend.model.traveldata.TravelPlan;
import com.valeriotor.iWanderBackend.model.traveldata.TravelPlanRedux;
import com.valeriotor.iWanderBackend.model.userdata.Profile;
import com.valeriotor.iWanderBackend.util.IntRange;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TravelPlanDataHandlerTests {

    @Test
    public void testGetTravelsByUser() {
        TravelPlanDataHandler dataHandler = DataHandlers.getTravelPlanDataHandler();
        long userId = 0;
        List<TravelPlanRedux> plansBy0 = dataHandler.getTravelsForUser(userId, IntRange.of(0, 4));
        assert plansBy0.size() == 3;
    }

    @Test
    public void testGetTravelByUserAndTravelID() {
        TravelPlanDataHandler dataHandler = DataHandlers.getTravelPlanDataHandler();
        long userId = 0;
        TravelPlanRedux firstPlan = getFirstTravel(dataHandler, userId);
        assert firstPlan.getName().equals(dataHandler.getTravel(userId, firstPlan.getId()).map(TravelPlan::getName).get());
    }

    @Test
    public void testUpdateTravel() {
        TravelPlanDataHandler dataHandler = DataHandlers.getTravelPlanDataHandler();
        long userId = 0;
        TravelPlanRedux firstPlanRedux = getFirstTravel(dataHandler, userId);
        TravelPlan firstPlan = dataHandler.getTravel(userId, firstPlanRedux.getId()).get();
        String newName = firstPlan.getName().concat("TheSecond");
        TravelPlan newPlan = new TravelPlan(firstPlan.getId(), newName, firstPlan.getVisibility(), firstPlan.getDays());
        dataHandler.updateTravel(userId, newPlan);
        assert dataHandler.getTravel(userId, newPlan.getId()).get().getName().equals(newName);
    }

    private TravelPlanRedux getFirstTravel(TravelPlanDataHandler dataHandler, long userId) {
        List<TravelPlanRedux> plansBy0 = dataHandler.getTravelsForUser(userId, IntRange.of(0, 4));
        return plansBy0.get(0);
    }

}
