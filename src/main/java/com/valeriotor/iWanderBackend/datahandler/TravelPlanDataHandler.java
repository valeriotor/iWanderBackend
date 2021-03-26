package com.valeriotor.iWanderBackend.datahandler;

import com.google.common.collect.ImmutableList;
import com.valeriotor.iWanderBackend.datahandler.repos.CityRepo;
import com.valeriotor.iWanderBackend.datahandler.repos.DayRepo;
import com.valeriotor.iWanderBackend.datahandler.repos.LocationTimeRepo;
import com.valeriotor.iWanderBackend.datahandler.repos.TravelPlanRepo;
import com.valeriotor.iWanderBackend.model.traveldata.*;
import com.valeriotor.iWanderBackend.util.IntRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Transactional
public class TravelPlanDataHandler {

    @Autowired
    private TravelPlanRepo planRepo;
    @Autowired
    private DayRepo dayRepo;
    @Autowired
    private CityRepo cityRepo;
    @Autowired
    private LocationTimeRepo locationTimeRepo;

    public void addTravel(TravelPlan plan) {
        planRepo.save(plan).getId();
    }

    public boolean renameTravel(long travelId, String newName) {
        Optional<TravelPlan> firstPlanOptional = planRepo.findById(travelId);
        if(firstPlanOptional.isPresent()) {
            TravelPlan newPlan = firstPlanOptional.get().withName(newName);
            planRepo.deleteById(travelId);
            planRepo.save(newPlan);
            return true;
        }
        return false;
    }

    public void deleteTravel(long travelId) {
        planRepo.deleteById(travelId);
    }

    public void updateTravel(TravelPlan updatedPlan) {
        planRepo.deleteById(updatedPlan.getId());
        planRepo.flush();
        planRepo.save(updatedPlan);
    }

    public List<TravelPlanRedux> getTravelsForUser(long userId, IntRange range) {
        if(range == null) return ImmutableList.of();
        List<TravelPlan> plans = planRepo.findAllByUserIdIn(ImmutableList.of(userId));
        plans.sort(null);
        List<TravelPlanRedux> sublist = range.getSublist(plans.stream().map(TravelPlanRedux::new).collect(Collectors.toList()));
        return sublist;
    }

    public Optional<TravelPlan> getTravel(long travelId) {
        Optional<TravelPlan> byId = planRepo.findById(travelId);
        byId.ifPresent(t -> t.getDays().size());
        return byId;
    }

    public List<Day> getDaysByTravelId(long travelId) {
        List<Day> days = planRepo.findById(travelId).map(TravelPlan::getDays).orElse(new ArrayList<>());
        days.sort(null);
        return days;
    }

    public List<LocationTime> getLocationTimesForDayAtIndex(long travelId, int dayIndex) {
        List<Day> days = dayRepo.findAllByTravelPlan_Id(travelId);
        days.sort(null);
        List<LocationTime> locationTimes = days.get(dayIndex).getLocationTimes();
        return locationTimes;
    }

    public List<LocationTime> getLocationTimesByDayId(long dayId) {
        List<LocationTime> locationTimes = dayRepo.findById(dayId).map(Day::getLocationTimes).orElse(new ArrayList<>());
        return locationTimes;
    }

    public List<List<LocationTime>> getLocationTimesByDaysIn(List<Day> days) {
        return days.stream().map(Day::getLocationTimes).collect(Collectors.toList());
    }

    public Optional<City> getCityById(String id) {
        return cityRepo.findById(id);
    }

}
