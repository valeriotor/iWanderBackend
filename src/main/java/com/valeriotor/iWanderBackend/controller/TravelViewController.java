package com.valeriotor.iWanderBackend.controller;

import com.google.common.collect.ImmutableList;
import com.valeriotor.iWanderBackend.datahandler.TravelPlanDataHandler;
import com.valeriotor.iWanderBackend.model.core.Day;
import com.valeriotor.iWanderBackend.model.core.LocationTime;
import com.valeriotor.iWanderBackend.model.core.TravelPlan;
import com.valeriotor.iWanderBackend.model.dto.DayMinimumDTO;
import com.valeriotor.iWanderBackend.model.dto.LocationTimeDTO;
import com.valeriotor.iWanderBackend.model.dto.TravelPlanDTO;
import com.valeriotor.iWanderBackend.model.dto.TravelPlanMinimumDTO;
import com.valeriotor.iWanderBackend.util.IntRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TravelViewController {

    @Autowired
    private TravelPlanDataHandler travelPlanDataHandler;

    @RequestMapping("/getTravels")
    public List<TravelPlanMinimumDTO> getTravelsByUserID(String username, Pageable pageable) {
        return travelPlanDataHandler.getTravelsForUser(username, pageable);
    }

    @RequestMapping("/getDays")
    public List<DayMinimumDTO> getDaysForTravel(long travelId, Pageable pageable) {
        List<Day> days = travelPlanDataHandler.getDaysByTravelId(travelId, pageable);
        return days.stream().map(DayMinimumDTO::new).collect(Collectors.toList());
    }

    @RequestMapping("/getLocationTimes")
    public List<LocationTimeDTO> getLocationTimesForDay(long travelId, int dayIndex, Pageable pageable) {
        return travelPlanDataHandler.getLocationTimesForDayAtIndex(travelId, dayIndex, pageable);
    }

    @RequestMapping("/renameTravel")
    public void renameTravel(long travelId, String newName) {
        travelPlanDataHandler.renameTravel(travelId, newName);
    }

    @RequestMapping("/deleteTravel")
    public void deleteTravel(long travelId) {
        travelPlanDataHandler.deleteTravel(travelId);
    }

    @RequestMapping("/addTravel")
    public void addTravel(@RequestBody TravelPlanDTO travelPlan) {
        travelPlanDataHandler.addTravel(travelPlan);
    }


}
