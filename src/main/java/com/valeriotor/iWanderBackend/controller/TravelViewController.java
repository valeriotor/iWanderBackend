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
    public TextDTO addTravel(@RequestBody TravelPlanDTO travelPlan) {
        long id = travelPlanDataHandler.addTravel(travelPlan);
        var dto = new TextDTO();
        dto.setText(String.valueOf(id));
        return dto;
    }

    @PutMapping("/travel/update")
    public TextDTO updateTravel(@RequestBody TravelPlanDTO travelPlan) {
        long id = travelPlanDataHandler.updateTravel(travelPlan);
        var dto = new TextDTO();
        dto.setText(String.valueOf(id));
        return dto;
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
        return travelPlanDataHandler.getImageUrls(travelId).stream().map(this::stringToTextDTO).collect(Collectors.toList());
    }

    @GetMapping("/travel/{travelId}/{dayIndex}/comments")
    public List<CommentDTO> getDayComments(@PathVariable long travelId, @PathVariable int dayIndex, Pageable pageable) {
        return travelPlanDataHandler.getDayComments(travelId, dayIndex, pageable);
    }

    @GetMapping("/travel/{travelId}/comments")
    public List<CommentDTO> getTravelComments(@PathVariable long travelId, Pageable pageable) {
        return travelPlanDataHandler.getTravelComments(travelId, pageable);
    }

    private TextDTO stringToTextDTO(String s) {
        var dto = new TextDTO();
        dto.setText(s);
        return dto;
    }

    @GetMapping("/travel/{travelId}/routes")
    public List<DayRouteDTO> getTravelRoutes(@PathVariable long travelId) {
        return travelPlanDataHandler.getTravelRoutes(travelId);
    }

    //@RequestMapping(value = "/travel/{travelId}/updateComments", method = {RequestMethod.POST, RequestMethod.PUT})
    //public void updateComments(@PathVariable long travelId, @RequestBody List<CommentDTO> commentDTOS) {
    //    travelPlanDataHandler.setComments(travelId, commentDTOS);
    //}

    @RequestMapping(value = "/travel/{travelId}/{dayIndex}/updateComments", method = {RequestMethod.POST, RequestMethod.PUT})
    public void updateCommentsDay(@PathVariable long travelId, @PathVariable int dayIndex, @RequestBody List<CommentDTO> textDTOS) {
        travelPlanDataHandler.setComments(travelId, dayIndex, textDTOS);
    }

}
