package com.valeriotor.iWanderBackend.controller;

import com.google.gson.reflect.TypeToken;
import com.valeriotor.iWanderBackend.controller.TravelViewController.DayRedux;
import com.valeriotor.iWanderBackend.model.traveldata.LocationTime;
import com.valeriotor.iWanderBackend.model.traveldata.TravelPlanRedux;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TravelViewControllerTests {

    @Autowired
    private TravelViewController testController;

    @Test
    public void testGetTravelsByUserID() {
        List<TravelPlanRedux> travelPlanReduxes = testController.getTravelsByUserID(-5, 0, 4);
        Type type = new TypeToken<ArrayList<TravelPlanRedux>>(){}.getType();
        assert travelPlanReduxes.size() == 3;
        assert travelPlanReduxes.get(2).getName().equals("Zurigo");
        long id = travelPlanReduxes.get(0).getId();
        testGetDaysForTravel(id);
    }

    private void testGetDaysForTravel(long travelId) {
        List<DayRedux> dayReduxes = testController.getDaysForTravel(travelId, 0, 4);
        assert dayReduxes.get(0).date.equals(LocalDate.of(2021, 9, 1));
        testGetLocationTimesForDay(travelId);
    }

    private void testGetLocationTimesForDay(long travelId) {
        List<LocationTime> locationTimesForDay = testController.getLocationTimesForDay(travelId,0, 0, 4);
        assert locationTimesForDay.size() == 1;
        assert locationTimesForDay.get(0).getName().equals("Pantheon");
    }

}
