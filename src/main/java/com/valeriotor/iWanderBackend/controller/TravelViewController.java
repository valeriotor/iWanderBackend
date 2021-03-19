package com.valeriotor.iWanderBackend.controller;

import com.google.common.collect.ImmutableList;
import com.valeriotor.iWanderBackend.datahandler.TravelPlanDataHandler;
import com.valeriotor.iWanderBackend.model.traveldata.City;
import com.valeriotor.iWanderBackend.model.traveldata.Day;
import com.valeriotor.iWanderBackend.model.traveldata.LocationTime;
import com.valeriotor.iWanderBackend.model.traveldata.TravelPlanRedux;
import com.valeriotor.iWanderBackend.util.IntRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
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
    public List<DayRedux> getDaysForTravel(long userId, long travelId, int start, int end) {
        IntRange range = IntRange.of(start, end);
        if(range == null) return ImmutableList.of();
        List<Day> days = range.getSublist(travelPlanDataHandler.getDaysByTravelId(userId, travelId));
        return days.stream().map(DayRedux::new).collect(Collectors.toList());
    }

    @RequestMapping("/getLocationTimes")
    public List<LocationTime> getLocationTimesForDay(long userId, long travelId, int dayIndex, int start, int end) {
        List<Day> days = travelPlanDataHandler.getDaysByTravelId(userId, travelId);
        IntRange range = IntRange.of(start, end);
        if(range == null) return ImmutableList.of();
        if(dayIndex >= days.size() || dayIndex < 0) return ImmutableList.of();
        Day d = days.get(dayIndex);
        return travelPlanDataHandler.getLocationTimesByDayId(d.getId());
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
