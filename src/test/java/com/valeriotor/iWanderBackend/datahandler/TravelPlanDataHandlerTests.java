package com.valeriotor.iWanderBackend.datahandler;

import com.valeriotor.iWanderBackend.controller.SerializableTravelPlan;
import com.valeriotor.iWanderBackend.controller.serializable.SerializableDay;
import com.valeriotor.iWanderBackend.model.traveldata.Day;
import com.valeriotor.iWanderBackend.model.traveldata.LocationTime;
import com.valeriotor.iWanderBackend.model.traveldata.TravelPlan;
import com.valeriotor.iWanderBackend.model.traveldata.TravelPlanRedux;
import com.valeriotor.iWanderBackend.util.IntRange;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        TravelPlanRedux firstPlan = getFirstTravel(dataHandler, userId);
        assert firstPlan.getName().equals(dataHandler.getTravel(userId, firstPlan.getId()).map(TravelPlan::getName).get());
    }

    @Test
    @Transactional
    public void testUpdateTravel() {
        long userId = -5;
        TravelPlanRedux firstPlanRedux = getFirstTravel(dataHandler, userId);
        TravelPlan firstPlan = dataHandler.getTravel(userId, firstPlanRedux.getId()).get();
        String newName = firstPlan.getName().concat("TheSecond");
        List<Day> days = dataHandler.getDaysByTravelId(firstPlan.getUserId(), firstPlan.getId());
        List<List<LocationTime>> times = days.stream().map(Day::getId).map(dataHandler::getLocationTimesByDayId).collect(Collectors.toList());
        List<SerializableDay> days1 = new ArrayList<>();
        for(int i = 0; i < days.size(); i++) {
            Day d = days.get(i);
            List<LocationTime> t = times.get(i);
            days1.add(new SerializableDay(firstPlan.getUserId(), firstPlan.getId(), d.getId(), d.getDate(), d.getCityID(), t));
        }
        SerializableTravelPlan newPlan = new SerializableTravelPlan(firstPlan.getId(), firstPlan.getUserId(), firstPlan.getName(), firstPlan.getVisibility(), firstPlan.getStartDate(), firstPlan.getStartDate(), days1);
        //dataHandler.updateTravel(userId, newPlan);
        //assert dataHandler.getTravel(userId, newPlan.getId()).get().getName().equals(newName);
    }

    private TravelPlanRedux getFirstTravel(TravelPlanDataHandler dataHandler, long userId) {
        List<TravelPlanRedux> plansByMinus5 = dataHandler.getTravelsForUser(userId, IntRange.of(0, 4));
        return plansByMinus5.get(0);
    }

}
