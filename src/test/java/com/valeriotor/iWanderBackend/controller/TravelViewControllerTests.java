package com.valeriotor.iWanderBackend.controller;

import com.valeriotor.iWanderBackend.datahandler.repos.UserDetailsRepo;
import com.valeriotor.iWanderBackend.model.VisibilityType;
import com.valeriotor.iWanderBackend.model.dto.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TravelViewControllerTests {

    @Autowired
    private TravelViewController travelViewController;
    @Autowired
    private UserDetailsRepo detailsRepo;

    @Test
    public void testGetTravelsByUserID() {
        List<TravelPlanMinimumDTO> travelPlanMinimumDTOS = travelViewController.getTravelsByUserID("valeriotor", 0, 4);
        assert travelPlanMinimumDTOS.size() == 3;
        assert travelPlanMinimumDTOS.get(2).getStartDate().equals(LocalDate.of(2021, 10, 1));
        long id = travelPlanMinimumDTOS.get(0).getId();
        testGetDaysForTravel(id);
    }

    private void testGetDaysForTravel(long travelId) {
        List<DayMinimumDTO> dayReduxes = travelViewController.getDaysForTravel(travelId, 0, 4);
        assert dayReduxes.get(0).getDate().equals(LocalDate.of(2021, 9, 1));
        testGetLocationTimesForDay(travelId);
    }

    private void testGetLocationTimesForDay(long travelId) {
        List<LocationTimeDTO> locationTimesForDay = travelViewController.getLocationTimesForDay(travelId,0, 0, 4);
        assert locationTimesForDay.size() == 1;
        assert locationTimesForDay.get(0).getName().equals("Pantheon");
    }

    @Test
    public void testAddTravel() {
        List<DayDTO> days = new ArrayList<>();
        List<LocationTimeDTO> locationTimes = new ArrayList<>();
        TravelPlanDTO planDTO = new TravelPlanDTO("Berlin", VisibilityType.PUBLIC, LocalDate.now(), days);

        DayDTO dayDTO = new DayDTO(LocalDate.now(), "dummy", planDTO, locationTimes);
        days.add(dayDTO);

        LocationTimeDTO locationTimeDTO = new LocationTimeDTO(LocalTime.of(23,10), 0, 0, "Brandenburg Gate", "dummy", dayDTO);
        locationTimes.add(locationTimeDTO);
        travelViewController.addTravel(planDTO);
        long id = 0;
        List<TravelPlanMinimumDTO> valsTravels = travelViewController.getTravelsByUserID("valeriotor", 0, 10);
        List<DayMinimumDTO> valsBerlinDays = null;
        List<LocationTimeDTO> valsFirstBerlinDayLocationTimes = null;
        for(TravelPlanMinimumDTO planMinimumDTO : valsTravels) {
            if(planMinimumDTO.getName().equals("Berlin")) {
                id = planMinimumDTO.getId();
                valsBerlinDays = travelViewController.getDaysForTravel(id, 0, 10);
                valsFirstBerlinDayLocationTimes = travelViewController.getLocationTimesForDay(id, 0, 0, 10);
                break;
            }
        }
        assert valsBerlinDays.size() == 1;
        assert valsFirstBerlinDayLocationTimes.size() == 1;
        assert valsFirstBerlinDayLocationTimes.get(0).getName().equals("Brandenburg Gate");
        travelViewController.deleteTravel(id);
    }

}
