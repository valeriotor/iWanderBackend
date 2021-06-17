package com.valeriotor.iWanderBackend.controller;

import com.valeriotor.iWanderBackend.datahandler.TravelPlanDataHandler;
import com.valeriotor.iWanderBackend.model.core.Day;
import com.valeriotor.iWanderBackend.model.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @RequestMapping(value = "/travel/{travelId}/rename", method = {RequestMethod.POST, RequestMethod.PUT})
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

    @RequestMapping(value = "/travel/{travelId}/addImage", method = {RequestMethod.POST, RequestMethod.PUT})
    public void addTravelImage(@PathVariable long travelId, @RequestParam MultipartFile image) throws IOException {
        travelPlanDataHandler.addImageToTravel(travelId, image.getBytes());
    }

    @DeleteMapping("/travel/{travelId}/deleteImage")
    public void deleteTravelImage(@PathVariable long travelId, @RequestParam String imageUrl) {
        travelPlanDataHandler.removeImageFromTravel(travelId, imageUrl);
    }

    @GetMapping("/travel/{travelId}/images")
    public List<TextDTO> getTravelImages(@PathVariable long travelId) {
        return travelPlanDataHandler.getImageUrls(travelId).stream().map(string -> {
            var dto = new TextDTO();
            dto.setText(string);
            return dto;
        }).collect(Collectors.toList());
    }

}
