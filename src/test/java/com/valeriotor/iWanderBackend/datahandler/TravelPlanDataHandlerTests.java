package com.valeriotor.iWanderBackend.datahandler;

import com.valeriotor.iWanderBackend.model.core.Day;
import com.valeriotor.iWanderBackend.model.core.LocationTime;
import com.valeriotor.iWanderBackend.model.core.TravelPlan;
import com.valeriotor.iWanderBackend.model.dto.TravelPlanMinimumDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TravelPlanDataHandlerTests {

    @Autowired
    private TravelPlanDataHandler dataHandler;

    @Test
    public void testGetTravelsByUser() {
        List<TravelPlanMinimumDTO> plansBy0 = dataHandler.getTravelsForUser("valeriotor", PageRequest.of(0, 4));
        assertEquals(3, plansBy0.size());
    }

    @Test
    public void testGetTravelByUserAndTravelID() {
        TravelPlanMinimumDTO firstPlan = getFirstTravelRedux(dataHandler);
        assert firstPlan.getName().equals(dataHandler.getTravelPlan(firstPlan.getId()).map(TravelPlan::getName).get());
    }

    @Test
    public void testUpdateTravel() throws CloneNotSupportedException {
        TravelPlan firstPlan = getFirstTravel(dataHandler);
        List<Day> days = firstPlan.getDays();
        List<List<LocationTime>> locationTimes = days.stream().map(Day::getLocationTimes).collect(toList());

        TravelPlan newPlan = new TravelPlan(firstPlan.getUser(), firstPlan.getId(), firstPlan.getName(), firstPlan.getVisibility(), new ArrayList<>());

        List<Day> clonedDays = days.stream().map(d -> new Day(0, d.getDate(), d.getCityId(), newPlan, new ArrayList<>())).collect(toList());

        for(int i = 0; i < clonedDays.size(); i++) {
            Day d = clonedDays.get(i);
            List<LocationTime> copied = locationTimes.get(i).stream()
                    .map(lt -> new LocationTime(0, lt.getTimeStamp(), lt.getLatitude(), lt.getLongitude(), lt.getName(), lt.getNameId(), d))
                    .collect(toList());
            d.addAllLocationTimes(copied);
        }
        newPlan.addAllDays(clonedDays);
        dataHandler.updateTravel(newPlan);
        TravelPlan updatedPlan = getFirstTravel(dataHandler);
        List<Day> newPlanDays = updatedPlan.getDays();
        List<LocationTime> newPlanFirstDayLocationTimes = newPlanDays.get(0).getLocationTimes();
        assertEquals(newPlanDays.size(), days.size());
        assertEquals(newPlanFirstDayLocationTimes.size(), days.get(0).getLocationTimes().size());
    }

    private TravelPlanMinimumDTO getFirstTravelRedux(TravelPlanDataHandler dataHandler) {
        List<TravelPlanMinimumDTO> plansByMinus5 = dataHandler.getTravelsForUser("valeriotor", PageRequest.of(0, 4, Sort.by("startDate")));
        return plansByMinus5.get(0);
    }

    private TravelPlan getFirstTravel(TravelPlanDataHandler dataHandler) {
        return dataHandler.getTravelPlan(getFirstTravelRedux(dataHandler).getId()).get();
    }

}
