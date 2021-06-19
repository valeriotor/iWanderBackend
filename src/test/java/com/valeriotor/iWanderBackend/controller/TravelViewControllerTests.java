package com.valeriotor.iWanderBackend.controller;

import com.google.common.collect.Lists;
import com.valeriotor.iWanderBackend.datahandler.repos.UserDetailsRepo;
import com.valeriotor.iWanderBackend.model.VisibilityType;
import com.valeriotor.iWanderBackend.model.dto.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

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
        List<TravelPlanMinimumDTO> travelPlanMinimumDTOS = travelViewController.getTravelsByUserID("valeriotor", PageRequest.of(0, 4, Sort.by("startDate")));
        assert travelPlanMinimumDTOS.size() == 3;
        assert travelPlanMinimumDTOS.get(2).getStartDate().equals(LocalDate.of(2021, 10, 1));
        long id = travelPlanMinimumDTOS.get(0).getId();
        testGetDaysForTravel(id);
    }

    private void testGetDaysForTravel(long travelId) {
        List<DayMinimumDTO> dayReduxes = travelViewController.getDaysForTravel(travelId, PageRequest.of(0, 4, Sort.by("date")));
        assert dayReduxes.get(0).getDate().equals(LocalDate.of(2021, 9, 1));
        testGetLocationTimesForDay(travelId);
    }

    private void testGetLocationTimesForDay(long travelId) {
        List<LocationTimeDTO> locationTimesForDay = travelViewController.getLocationTimesForDay(travelId,0, PageRequest.of(0, 4, Sort.by("timeStamp")));
        assert locationTimesForDay.size() == 1;
        assert locationTimesForDay.get(0).getName().equals("Pantheon");
    }

    @Test
    public void testAddTravel() {
        List<DayDTO> days = new ArrayList<>();
        List<LocationTimeDTO> locationTimes = new ArrayList<>();
        List<String> locationComments = Lists.newArrayList("Nice place!");
        TravelPlanDTO planDTO = new TravelPlanDTO(0, "Berlin", VisibilityType.PUBLIC, LocalDate.now(), days);

        DayDTO dayDTO = new DayDTO(LocalDate.now(), "dummy", planDTO, locationTimes);
        days.add(dayDTO);

        CoordinateDTO[] coordinateDTOS = new CoordinateDTO[10];
        for (int i = 0; i < 10; i++) {
            coordinateDTOS[i] = new CoordinateDTO(1.1*i, 1.1*i);
        }
        DayRouteDTO routeDTO = new DayRouteDTO();
        routeDTO.setRoute(coordinateDTOS);
        routeDTO.setDay(dayDTO);
        dayDTO.setRoute(routeDTO);

        LocationTimeDTO locationTimeDTO = new LocationTimeDTO(LocalTime.of(23,10), 0, 0, "Brandenburg Gate", "dummy", dayDTO);
        dayDTO.setComments(locationComments);
        locationTimes.add(locationTimeDTO);
        travelViewController.addTravel(planDTO);
        long id = 0;
        List<TravelPlanMinimumDTO> valsTravels = travelViewController.getTravelsByUserID("valeriotor", PageRequest.of(0, 10));
        List<DayMinimumDTO> valsBerlinDays = null;
        List<LocationTimeDTO> valsFirstBerlinDayLocationTimes = null;
        List<TextDTO> dtos = null;
        for(TravelPlanMinimumDTO planMinimumDTO : valsTravels) {
            if(planMinimumDTO.getName().equals("Berlin")) {
                id = planMinimumDTO.getId();
                valsBerlinDays = travelViewController.getDaysForTravel(id, PageRequest.of(0, 10));
                valsFirstBerlinDayLocationTimes = travelViewController.getLocationTimesForDay(id, 0, PageRequest.of(0, 10, Sort.by("timeStamp")));
                //dtos = travelViewController.getDayComments(id, 0, PageRequest.of(0, 20));
                break;
            }
        }
        assert valsBerlinDays.size() == 1;
        assert valsFirstBerlinDayLocationTimes.size() == 1;
        assert valsFirstBerlinDayLocationTimes.get(0).getName().equals("Brandenburg Gate");

        testGetRoutes(id, coordinateDTOS);
        //assert dtos.get(0).getText().equals("Nice place!");
        testSetComments(id);
        travelViewController.deleteTravel(id);
    }

    private void testGetRoutes(long travelId, CoordinateDTO[] coordinateDTOS) {
        List<DayRouteDTO> travelRoutes = travelViewController.getTravelRoutes(travelId);
        CoordinateDTO[] deconverted = travelRoutes.get(0).getRoute();
        for (int i = 0; i < deconverted.length; i++) {
            assert deconverted[i].equals(coordinateDTOS[i]);
        }
    }

    private void testSetComments(long travelId) {
        CommentDTO commentDTO = new CommentDTO(0, "Nice place!");
        CommentDTO commentDTO1 = new CommentDTO(0, "Ok not so nice");
        travelViewController.updateComments(travelId, Lists.newArrayList(commentDTO, commentDTO1));
        List<TextDTO> dayComments = travelViewController.getDayComments(travelId, 0, PageRequest.of(0, 20));
        assert dayComments.size() == 2;
        assert dayComments.get(0).getText().equals("Nice place!");
        assert dayComments.get(1).getText().equals("Ok not so nice");
    }

}
