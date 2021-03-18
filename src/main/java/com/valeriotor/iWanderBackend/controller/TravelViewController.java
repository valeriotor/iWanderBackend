package com.valeriotor.iWanderBackend.controller;

import com.google.common.collect.ImmutableList;
import com.valeriotor.iWanderBackend.datahandler.DataHandlers;
import com.valeriotor.iWanderBackend.model.traveldata.Day;
import com.valeriotor.iWanderBackend.model.traveldata.LocationTime;
import com.valeriotor.iWanderBackend.model.traveldata.TravelPlan;
import com.valeriotor.iWanderBackend.model.traveldata.TravelPlanRedux;
import com.valeriotor.iWanderBackend.util.GSONUtil;
import com.valeriotor.iWanderBackend.util.IntRange;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class TravelViewController {

    @RequestMapping("/getTravel")
    public List<TravelPlanRedux> getTravelsByUserID(long userId, int start, int end) {
        return DataHandlers.getTravelPlanDataHandler().getTravelsForUser(userId, IntRange.of(start, end));
    }

    @RequestMapping("/getDays")
    public List<DayRedux> getDaysForTravel(long userId, int travelId, int start, int end) {
        Optional<TravelPlan> planOptional = DataHandlers.getTravelPlanDataHandler().getTravel(userId, travelId);
        IntRange range = IntRange.of(start, end);
        if(planOptional.isEmpty()) return ImmutableList.of();
        if(range == null) return ImmutableList.of();
        TravelPlan plan = planOptional.get();
        List<DayRedux> dayList = plan.getDays().stream().map(DayRedux::new).collect(Collectors.toCollection(ArrayList::new));
        return range.getSublist(dayList);
    }

    @RequestMapping("/getLocationTimes")
    public List<LocationTime> getLocationTimesForDay(long userId, int travelId, int dayIndex, int start, int end) {
        Optional<TravelPlan> planOptional = DataHandlers.getTravelPlanDataHandler().getTravel(userId, travelId);
        IntRange range = IntRange.of(start, end);
        if(range == null) return ImmutableList.of();
        if(planOptional.isEmpty()) return ImmutableList.of();
        List<Day> days = planOptional.get().getDays();
        if(dayIndex < 0 || dayIndex >= days.size()) return ImmutableList.of();
        Day day = days.get(dayIndex);
        return range.getSublist(day.getDestinations());
    }


    public static class DayRedux {
        final String cityName;
        final LocalDate date;

        private DayRedux(Day day) {
            this.cityName = day.getCity() != null ? day.getCity().getName() : "DummyCity";
            this.date = day.getDate();
        }
    }

}
