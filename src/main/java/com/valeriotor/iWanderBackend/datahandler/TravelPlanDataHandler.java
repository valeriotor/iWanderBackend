package com.valeriotor.iWanderBackend.datahandler;

import com.google.common.collect.ImmutableList;
import com.valeriotor.iWanderBackend.controller.SerializableTravelPlan;
import com.valeriotor.iWanderBackend.controller.serializable.SerializableDay;
import com.valeriotor.iWanderBackend.datahandler.repos.CityRepo;
import com.valeriotor.iWanderBackend.datahandler.repos.DayRepo;
import com.valeriotor.iWanderBackend.datahandler.repos.LocationTimeRepo;
import com.valeriotor.iWanderBackend.datahandler.repos.TravelPlanRepo;
import com.valeriotor.iWanderBackend.model.traveldata.*;
import com.valeriotor.iWanderBackend.util.IntRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TravelPlanDataHandler {

    @Autowired
    private TravelPlanRepo planRepo;
    @Autowired
    private DayRepo dayRepo;
    @Autowired
    private CityRepo cityRepo;
    @Autowired
    private LocationTimeRepo locationTimeRepo;

    public void addTravel(SerializableTravelPlan plan) {
        planRepo.save(plan.toTravelPlan());
        dayRepo.saveAll(plan.getDays().stream().map(SerializableDay::toDay).collect(Collectors.toList()));
        List<LocationTime> locationTimes = plan.getDays().stream().flatMap(SerializableDay::getLocationTimesStream).collect(Collectors.toList());
        locationTimeRepo.saveAll(locationTimes);
    }

    public void updateTravel(long userId, SerializableTravelPlan travelPlan) {
        List<Day> days = dayRepo.findAllByUserIdAndTravelPlanId(userId, travelPlan.getId());
        locationTimeRepo.deleteAllByDayIdIn(days.stream().map(Day::getId).collect(Collectors.toList()));
        dayRepo.deleteAll(days);
        planRepo.deleteById(new TravelPlan.TravelPlanID(userId, travelPlan.getId()));
        planRepo.save(travelPlan.toTravelPlan());
        List<SerializableDay> days1 = travelPlan.getDays();
        dayRepo.saveAll(days1.stream().map(SerializableDay::toDay).collect(Collectors.toList()));
        locationTimeRepo.saveAll(days1.stream().flatMap(SerializableDay::getLocationTimesStream).collect(Collectors.toList()));
    }

    public List<TravelPlanRedux> getTravelsForUser(long userId, IntRange range) {
        if(range == null) return ImmutableList.of();
        List<TravelPlan> plans = planRepo.findAllByUserIdIn(ImmutableList.of(userId));
        plans.sort(null);
        return range.getSublist(plans.stream().map(TravelPlanRedux::new).collect(Collectors.toList()));
    }

    public Optional<TravelPlan> getTravel(long userId, long travelId) {
        return planRepo.findById(new TravelPlan.TravelPlanID(userId, travelId));
    }

    public List<Day> getDaysByTravelId(long userId, long travelId) {
        List<Day> days = dayRepo.findAllByUserIdAndTravelPlanId(userId, travelId);
        days.sort(null);
        return days;
    }

    public List<LocationTime> getLocationTimesByDayId(long dayId) {
        return locationTimeRepo.findByDayId(dayId);
    }

    public Optional<City> getCityById(String id) {
        return cityRepo.findById(id);
    }

}
