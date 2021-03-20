package com.valeriotor.iWanderBackend.controller;

import com.google.common.collect.ImmutableList;
import com.valeriotor.iWanderBackend.controller.serializable.SerializableDay;
import com.valeriotor.iWanderBackend.datahandler.TravelPlanDataHandler;
import com.valeriotor.iWanderBackend.model.traveldata.*;
import com.valeriotor.iWanderBackend.util.IntRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        List<Day> days = travelPlanDataHandler.getDaysByTravelId(travelId);
        IntRange range = IntRange.of(start, end);
        if(range == null) return ImmutableList.of();
        if(dayIndex >= days.size() || dayIndex < 0) return ImmutableList.of();
        Day d = days.get(dayIndex);
        return travelPlanDataHandler.getLocationTimesByDayId(d.getId());
    }

    @RequestMapping("/renameTravel")
    public void renameTravel(long travelId, String newName) {
        travelPlanDataHandler.renameTravel(travelId, newName);
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
