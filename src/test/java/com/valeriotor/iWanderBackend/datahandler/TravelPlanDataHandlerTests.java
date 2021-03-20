package com.valeriotor.iWanderBackend.datahandler;

import com.valeriotor.iWanderBackend.model.traveldata.*;
import com.valeriotor.iWanderBackend.util.IntRange;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TravelPlanDataHandlerTests {

    @Autowired
    private TravelPlanDataHandler dataHandler;

    @Test
    public void testGetTravelsByUser() {
        long userId = -5;
        List<TravelPlanRedux> plansBy0 = dataHandler.getTravelsForUser(userId, IntRange.of(0, 4));
        assert plansBy0.size() == 3;
    }

    @Test
    public void testGetTravelByUserAndTravelID() {
        long userId = -5;
        TravelPlanRedux firstPlan = getFirstTravelRedux(dataHandler, userId);
        assert firstPlan.getName().equals(dataHandler.getTravel(firstPlan.getId()).map(TravelPlan::getName).get());
    }

    @Test
    public void testUpdateTravel() {
        long userId = -5;
        TravelPlan firstPlan = getFirstTravel(dataHandler, userId);
        List<Day> firstPlanDays = dataHandler.getDaysByTravelId(firstPlan.getId());
        List<List<LocationTime>> firstPlanLocationTimes = dataHandler.getLocationTimesByDaysIn(firstPlanDays);
        Day lastDay = firstPlanDays.get(firstPlanDays.size()-1);
        firstPlanDays.add(new Day(firstPlan.getId(), 0, lastDay.getDate().plusDays(1), lastDay.getCityId()));
        firstPlanLocationTimes.add(new ArrayList<>());
        TravelDataContainer container = new TravelDataContainer(firstPlan, firstPlanDays, firstPlanLocationTimes);
        dataHandler.updateTravel(container);
        TravelPlan updatedPlan = getFirstTravel(dataHandler, userId);
        List<Day> newPlanDays = dataHandler.getDaysByTravelId(updatedPlan.getId());
        List<List<LocationTime>> newPlanLocationTimes = dataHandler.getLocationTimesByDaysIn(newPlanDays);
        assert newPlanDays.size() == firstPlanDays.size(); // Note: we already increased firstPlanDays size by one
        assert newPlanLocationTimes.get(0).size() == firstPlanLocationTimes.get(0).size();
    }

    private TravelPlanRedux getFirstTravelRedux(TravelPlanDataHandler dataHandler, long userId) {
        List<TravelPlanRedux> plansByMinus5 = dataHandler.getTravelsForUser(userId, IntRange.of(0, 4));
        return plansByMinus5.get(0);
    }

    private TravelPlan getFirstTravel(TravelPlanDataHandler dataHandler, long userId) {
        return dataHandler.getTravel(getFirstTravelRedux(dataHandler, userId).getId()).get();
    }

}
