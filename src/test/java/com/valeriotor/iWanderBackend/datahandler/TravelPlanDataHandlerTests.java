package com.valeriotor.iWanderBackend.datahandler;

import com.valeriotor.iWanderBackend.model.traveldata.*;
import com.valeriotor.iWanderBackend.util.IntRange;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

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
    public void testUpdateTravel() throws CloneNotSupportedException {
        long userId = -5;
        TravelPlan firstPlan = getFirstTravel(dataHandler, userId);
        List<Day> days = firstPlan.getDays();
        List<List<LocationTime>> locationTimes = days.stream().map(Day::getLocationTimes).collect(toList());

        TravelPlan newPlan = new TravelPlan(firstPlan.getUserId(), firstPlan.getId(), firstPlan.getName(), firstPlan.getVisibility(), new ArrayList<>());

        List<Day> clonedDays = days.stream().map(d -> new Day(d.getId(), d.getDate(), d.getCityId(), newPlan, new ArrayList<>())).collect(toList());

        for(int i = 0; i < clonedDays.size(); i++) {
            Day d = clonedDays.get(i);
            List<LocationTime> copied = locationTimes.get(i).stream()
                    .map(lt -> new LocationTime(lt.getLocationTimeId(), lt.getTimeStamp(), lt.getLatitude(), lt.getLongitude(), lt.getName(), lt.getNameId(), d))
                    .collect(toList());
            d.setLocationTimes(copied);
        }
        newPlan.setDays(clonedDays);
        dataHandler.updateTravel(newPlan);
        TravelPlan updatedPlan = getFirstTravel(dataHandler, userId);
        List<Day> newPlanDays = updatedPlan.getDays();
        List<LocationTime> newPlanFirstDayLocationTimes = newPlanDays.get(0).getLocationTimes();
        assert newPlanDays.size() == days.size();
        assert newPlanFirstDayLocationTimes.size() == days.get(0).getLocationTimes().size();
    }

    private TravelPlanRedux getFirstTravelRedux(TravelPlanDataHandler dataHandler, long userId) {
        List<TravelPlanRedux> plansByMinus5 = dataHandler.getTravelsForUser(userId, IntRange.of(0, 4));
        return plansByMinus5.get(0);
    }

    private TravelPlan getFirstTravel(TravelPlanDataHandler dataHandler, long userId) {
        return dataHandler.getTravel(getFirstTravelRedux(dataHandler, userId).getId()).get();
    }

}
