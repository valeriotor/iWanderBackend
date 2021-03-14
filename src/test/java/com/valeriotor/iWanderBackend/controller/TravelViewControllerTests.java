package com.valeriotor.iWanderBackend.controller;

import com.google.gson.reflect.TypeToken;
import com.valeriotor.iWanderBackend.controller.TravelViewController.DayRedux;
import com.valeriotor.iWanderBackend.model.traveldata.LocationTime;
import com.valeriotor.iWanderBackend.model.traveldata.TravelPlanRedux;
import com.valeriotor.iWanderBackend.util.GSONUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TravelViewControllerTests {

    private final TravelViewController testController = new TravelViewController();

    @Test
    public void testGetTravelsByUserID() {
        String json = testController.getTravelsByUserID(0, 0, 4);
        Type type = new TypeToken<ArrayList<TravelPlanRedux>>(){}.getType();
        List<TravelPlanRedux> travelPlanReduxes = GSONUtil.getGson().fromJson(json, type);
        assert travelPlanReduxes.size() == 3;
        assert travelPlanReduxes.get(2).getName().equals("Zurigo");
        int id = travelPlanReduxes.get(0).getId();
        testGetDaysForTravel(id);
    }

    private void testGetDaysForTravel(int travelId) {
        String json = testController.getDaysForTravel(0, travelId, 0, 4);
        Type type = new TypeToken<ArrayList<DayRedux>>(){}.getType();
        List<DayRedux> dayReduxes = GSONUtil.getGson().fromJson(json, type);
        assert dayReduxes.get(0).date.equals(LocalDate.of(2021, 10, 1));
        testGetLocationTimesForDay(travelId);
    }

    private void testGetLocationTimesForDay(int travelId) {
        String json = testController.getLocationTimesForDay(0, travelId,0, 0, 4);
        Type type = new TypeToken<ArrayList<LocationTime>>(){}.getType();
        List<LocationTime> dayReduxes = GSONUtil.getGson().fromJson(json, type);
        assert dayReduxes.size() == 1;
        assert dayReduxes.get(0).getName().equals("Pantheon");
    }

}
