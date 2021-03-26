package com.valeriotor.iWanderBackend.controller;

import com.google.common.collect.ImmutableList;
import com.valeriotor.iWanderBackend.controller.serializable.SerializableDay;
import com.valeriotor.iWanderBackend.datahandler.TravelPlanDataHandler;
import com.valeriotor.iWanderBackend.model.traveldata.*;
import com.valeriotor.iWanderBackend.util.IntRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class TravelViewController {

    @Autowired
    private TravelPlanDataHandler travelPlanDataHandler;

    @RequestMapping("/getTravel")
    public List<TravelPlanRedux> getTravelsByUserID(long userId, int start, int end) {
        return travelPlanDataHandler.getTravelsForUser(userId, IntRange.of(start, end));
    }

    @RequestMapping("/getDays")
    public List<DayRedux> getDaysForTravel(long travelId, int start, int end) {
        IntRange range = IntRange.of(start, end);
        if(range == null) return ImmutableList.of();
        List<Day> days = range.getSublist(travelPlanDataHandler.getDaysByTravelId(travelId));
        return days.stream().map(DayRedux::new).collect(Collectors.toList());
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


    public static class DayRedux {
        final String cityName;
        final LocalDate date;

        private DayRedux(Day day) {
            this.cityName = "DummyCity";
            this.date = day.getDate();
        }

        public String getCityName() {
            return cityName;
        }

        public LocalDate getDate() {
            return date;
        }
    }

}
