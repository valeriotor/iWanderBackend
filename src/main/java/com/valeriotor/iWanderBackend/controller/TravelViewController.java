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
    public String getTravelsByUserID(long userId, int start, int end) {
        List<TravelPlanRedux> planReduxList = DataHandlers.getTravelPlanDataHandler().getTravelsForUser(userId, IntRange.of(start, end));
        return GSONUtil.getGson().toJson(planReduxList);
    }

    @RequestMapping("/getDays")
    public String getDaysForTravel(long userId, int travelId, int start, int end) {
        Optional<TravelPlan> planOptional = DataHandlers.getTravelPlanDataHandler().getTravel(userId, travelId);
        IntRange range = IntRange.of(start, end);
        if(planOptional.isEmpty()) return "[]";
        if(range == null) return "[]";
        TravelPlan plan = planOptional.get();
        List<DayRedux> dayList = plan.getDays().stream().map(DayRedux::new).collect(Collectors.toCollection(ArrayList::new));
        List<DayRedux> toSend = range.getSublist(dayList);
        return GSONUtil.getGson().toJson(toSend);
    }

    @RequestMapping("/getLocationTimes")
    public String getLocationTimesForDay(long userId, int travelId, int dayIndex, int start, int end) {
        Optional<TravelPlan> planOptional = DataHandlers.getTravelPlanDataHandler().getTravel(userId, travelId);
        IntRange range = IntRange.of(start, end);
        if(range == null) return "[]";
        if(planOptional.isEmpty()) return "[]";
        List<Day> days = planOptional.get().getDays();
        if(dayIndex < 0 || dayIndex >= days.size()) return "[]";
        Day day = days.get(dayIndex);
        List<LocationTime> toSend = range.getSublist(day.getDestinations());
        return GSONUtil.getGson().toJson(toSend);
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
