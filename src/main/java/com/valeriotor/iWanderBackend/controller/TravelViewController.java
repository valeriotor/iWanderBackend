package com.valeriotor.iWanderBackend.controller;

import com.google.common.collect.ImmutableList;
import com.valeriotor.iWanderBackend.datahandler.TravelPlanDataHandler;
import com.valeriotor.iWanderBackend.model.core.Day;
import com.valeriotor.iWanderBackend.model.core.LocationTime;
import com.valeriotor.iWanderBackend.model.core.TravelPlan;
import com.valeriotor.iWanderBackend.model.dto.DayMinimumDTO;
import com.valeriotor.iWanderBackend.model.dto.TravelPlanMinimumDTO;
import com.valeriotor.iWanderBackend.util.IntRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TravelViewController {

    @Autowired
    private TravelPlanDataHandler travelPlanDataHandler;

    @RequestMapping("/getTravel")
    public List<TravelPlanMinimumDTO> getTravelsByUserID(String username, int start, int end) {
        //System.out.println(((ApplicationUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
        return travelPlanDataHandler.getTravelsForUser(username, IntRange.of(start, end));
    }

    @RequestMapping("/getDays")
    public List<DayMinimumDTO> getDaysForTravel(long travelId, int start, int end) {
        IntRange range = IntRange.of(start, end);
        if(range == null) return ImmutableList.of();
        List<Day> days = range.getSublist(travelPlanDataHandler.getDaysByTravelId(travelId));
        return days.stream().map(DayMinimumDTO::new).collect(Collectors.toList());
    }

    @RequestMapping("/getLocationTimes")
    public List<LocationTime> getLocationTimesForDay(long travelId, int dayIndex, int start, int end) {
        IntRange range = IntRange.of(start, end);
        if(range == null) return ImmutableList.of();
        return travelPlanDataHandler.getLocationTimesForDayAtIndex(travelId, dayIndex);
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
    public void addTravel(@RequestBody TravelPlan travelPlan) {
        travelPlanDataHandler.addTravel(travelPlan);
    }


}
