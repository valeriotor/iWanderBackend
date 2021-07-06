package com.valeriotor.iWanderBackend.controller;

import com.google.common.collect.Lists;
import com.valeriotor.iWanderBackend.datahandler.repos.UserDetailsRepo;
import com.valeriotor.iWanderBackend.model.VisibilityType;
import com.valeriotor.iWanderBackend.model.dto.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals(locationTimesForDay.size(), 1);
        assertEquals(locationTimesForDay.get(0).getName(), "Pantheon");
    }

    @Test
    public void testAddTravel() {
        List<DayDTO> days = new ArrayList<>();
        List<LocationTimeDTO> locationTimes = new ArrayList<>();
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
        List<CommentDTO> locationComments = Lists.newArrayList(new CommentDTO("place", "niceplace", "www.something.com", dayDTO));

        LocationTimeDTO locationTimeDTO = new LocationTimeDTO(LocalTime.of(23,10), 0, 0, "Brandenburg Gate", "dummy", dayDTO);
        dayDTO.setComments(locationComments);
        locationTimes.add(locationTimeDTO);
        travelViewController.addTravel(planDTO);
        long id = 0;
        List<TravelPlanMinimumDTO> valsTravels = travelViewController.getTravelsByUserID("valeriotor", PageRequest.of(0, 10));
        List<DayMinimumDTO> valsBerlinDays = null;
        List<LocationTimeDTO> valsFirstBerlinDayLocationTimes = null;
        List<CommentDTO> dtos = null;
        for(TravelPlanMinimumDTO planMinimumDTO : valsTravels) {
            if(planMinimumDTO.getName().equals("Berlin")) {
                id = planMinimumDTO.getId();
                valsBerlinDays = travelViewController.getDaysForTravel(id, PageRequest.of(0, 10));
                valsFirstBerlinDayLocationTimes = travelViewController.getLocationTimesForDay(id, 0, PageRequest.of(0, 10, Sort.by("timeStamp")));
                dtos = travelViewController.getDayComments(id, 0, PageRequest.of(0, 20));
                break;
            }
        }
        assertEquals(valsBerlinDays.size(), 1);
        assertEquals(valsFirstBerlinDayLocationTimes.size(), 1);
        assertEquals(valsFirstBerlinDayLocationTimes.get(0).getName(), "Brandenburg Gate");

        testGetRoutes(id, coordinateDTOS);
        assertEquals(dtos.get(0).getText(), "niceplace");
        testSetComments(id);
        travelViewController.deleteTravel(id);
    }

    private void testGetRoutes(long travelId, CoordinateDTO[] coordinateDTOS) {
        List<DayRouteDTO> travelRoutes = travelViewController.getTravelRoutes(travelId);
        CoordinateDTO[] deconverted = travelRoutes.get(0).getRoute();
        assertArrayEquals(deconverted, coordinateDTOS);
    }

    private void testSetComments(long travelId) {
        CommentDTO commentDTO = new CommentDTO("Place2", "niceplace2", "image", null);
        CommentDTO commentDTO1 = new CommentDTO("Place3", "niceplace3", "image2", null);
        travelViewController.updateCommentsDay(travelId, 0, Lists.newArrayList(commentDTO, commentDTO1));
        List<CommentDTO> commentDTOS = travelViewController.getDayComments(travelId, 0, PageRequest.of(0, 20));
        assertEquals(commentDTOS.size(), 2);
        assertEquals(commentDTOS.get(0).getText(), "niceplace2");
        assertEquals(commentDTOS.get(1).getText(), "niceplace3");
    }

    //private void testSetComments(long travelId) {
    //    CommentDTO commentDTO = new CommentDTO(0, "Nice place!");
    //    CommentDTO commentDTO1 = new CommentDTO(0, "Ok not so nice");
    //    travelViewController.updateComments(travelId, Lists.newArrayList(commentDTO, commentDTO1));
    //    List<TextDTO> dayComments = travelViewController.getDayComments(travelId, 0, PageRequest.of(0, 20));
    //    assert dayComments.size() == 2;
    //    assert dayComments.get(0).getText().equals("Nice place!");
    //    assert dayComments.get(1).getText().equals("Ok not so nice");
    //}

}
