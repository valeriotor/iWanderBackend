package com.valeriotor.iWanderBackend.controller;

import com.valeriotor.iWanderBackend.datahandler.TravelPlanDataHandler;
import com.valeriotor.iWanderBackend.model.core.Day;
import com.valeriotor.iWanderBackend.model.dto.DayMinimumDTO;
import com.valeriotor.iWanderBackend.model.dto.LocationTimeDTO;
import com.valeriotor.iWanderBackend.model.dto.TravelPlanDTO;
import com.valeriotor.iWanderBackend.model.dto.TravelPlanMinimumDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TravelViewController {

    @Autowired
    private TravelPlanDataHandler travelPlanDataHandler;

    @GetMapping("/travels/{username}")
    public List<TravelPlanMinimumDTO> getTravelsByUserID(@PathVariable String username, Pageable pageable) {
        return travelPlanDataHandler.getTravelsForUser(username, pageable);
    }

    @GetMapping("/travel/{travelId}")
    public TravelPlanMinimumDTO getTravel(@PathVariable long travelId) {
        return travelPlanDataHandler.getTravel(travelId);
    }

    @GetMapping("/travel/{travelId}/days")
    public List<DayMinimumDTO> getDaysForTravel(@PathVariable long travelId, Pageable pageable) {
        List<Day> days = travelPlanDataHandler.getDaysByTravelId(travelId, pageable);
        return days.stream().map(DayMinimumDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/travel/{travelId}/{dayIndex}/locations")
    public List<LocationTimeDTO> getLocationTimesForDay(@PathVariable long travelId, @PathVariable int dayIndex, Pageable pageable) {
        return travelPlanDataHandler.getLocationTimesForDayAtIndex(travelId, dayIndex, pageable);
    }

    @PutMapping("/travel/{travelId}/rename")
    public void renameTravel(@PathVariable long travelId, String newName) {
        travelPlanDataHandler.renameTravel(travelId, newName);
    }

    @DeleteMapping("/travel/{travelId}/delete")
    public void deleteTravel(@PathVariable long travelId) {
        travelPlanDataHandler.deleteTravel(travelId);
    }

    @PostMapping("/travel/add")
    public void addTravel(@RequestBody TravelPlanDTO travelPlan) {
        travelPlanDataHandler.addTravel(travelPlan);
    }

    @PutMapping("/travel/update")
    public void updateTravel(@RequestBody TravelPlanDTO travelPlan) {
        travelPlanDataHandler.updateTravel(travelPlan);
    }

}
