package com.valeriotor.iWanderBackend.controller;

import com.google.gson.reflect.TypeToken;
import com.valeriotor.iWanderBackend.model.dto.DayMinimumDTO;
import com.valeriotor.iWanderBackend.model.core.LocationTime;
import com.valeriotor.iWanderBackend.model.dto.TravelPlanMinimumDTO;
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
        List<TravelPlanMinimumDTO> travelPlanMinimumDTOS = testController.getTravelsByUserID("valeriotor", 0, 4);
        assert travelPlanMinimumDTOS.size() == 3;
        assert travelPlanMinimumDTOS.get(2).getStartDate().equals(LocalDate.of(2021, 10, 1));
        long id = travelPlanMinimumDTOS.get(0).getId();
        testGetDaysForTravel(id);
    }

    private void testGetDaysForTravel(long travelId) {
        List<DayMinimumDTO> dayReduxes = testController.getDaysForTravel(travelId, 0, 4);
        assert dayReduxes.get(0).getDate().equals(LocalDate.of(2021, 9, 1));
        testGetLocationTimesForDay(travelId);
    }

    private void testGetLocationTimesForDay(long travelId) {
        List<LocationTime> locationTimesForDay = testController.getLocationTimesForDay(travelId,0, 0, 4);
        assert locationTimesForDay.size() == 1;
        assert locationTimesForDay.get(0).getName().equals("Pantheon");
    }

}
