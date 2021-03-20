package com.valeriotor.iWanderBackend.datahandler;

import com.google.common.collect.ImmutableList;
import com.valeriotor.iWanderBackend.controller.serializable.SerializableDay;
import com.valeriotor.iWanderBackend.datahandler.repos.CityRepo;
import com.valeriotor.iWanderBackend.datahandler.repos.DayRepo;
import com.valeriotor.iWanderBackend.datahandler.repos.LocationTimeRepo;
import com.valeriotor.iWanderBackend.datahandler.repos.TravelPlanRepo;
import com.valeriotor.iWanderBackend.model.traveldata.*;
import com.valeriotor.iWanderBackend.util.IntRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
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

    public void addTravel(TravelDataContainer plan) {
        planRepo.save(plan.getTravelPlan());
        dayRepo.saveAll(plan.getDays());
        List<LocationTime> locationTimes = plan.getFlattenedLocationTimesPerDay();
        locationTimeRepo.saveAll(locationTimes);
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

    /** Updates TravelPlan, Days and LocationTimes based on the Id of the passed TravelDataContainer.
     *
     * @param newTravelData
     */
    public void updateTravel(TravelDataContainer newTravelData) {
        delete(newTravelData);
        planRepo.flush();
        locationTimeRepo.flush();
        save(newTravelData);
    }

    private void delete(TravelDataContainer newTravelData) {
        List<Day> days = dayRepo.findAllByTravelPlanId(newTravelData.getTravelPlan().getId());
        locationTimeRepo.deleteAllByDayIdIn(days.stream().map(Day::getId).collect(Collectors.toList()));
        dayRepo.deleteAll(days);
        planRepo.deleteById(newTravelData.getTravelPlan().getId());
    }

    private void save(TravelDataContainer newTravelData) {
        TravelPlan plan = planRepo.save(newTravelData.getTravelPlan());
        List<List<LocationTime>> allLocationTimes = newTravelData.getLocationTimesPerDay();
        int i = 0;
        for(Day day : newTravelData.getDays()) {
            Day dayWithNewTravelId = day.withTravelId(plan.getId());
            Day dayWithNewId = dayRepo.save(dayWithNewTravelId);
            List<LocationTime> locationTimes = allLocationTimes.get(i);
            List<LocationTime> locationTimesWithNewDayIds = locationTimes.stream().map(l -> l.withDayId(dayWithNewId.getId())).collect(Collectors.toList());
            locationTimeRepo.saveAll(locationTimesWithNewDayIds);
            i++;
        }
    }

    public List<TravelPlanRedux> getTravelsForUser(long userId, IntRange range) {
        if(range == null) return ImmutableList.of();
        List<TravelPlan> plans = planRepo.findAllByUserIdIn(ImmutableList.of(userId));
        plans.sort(null);
        return range.getSublist(plans.stream().map(TravelPlanRedux::new).collect(Collectors.toList()));
    }

    public Optional<TravelPlan> getTravel(long travelId) {
        return planRepo.findById(travelId);
    }

    public List<Day> getDaysByTravelId(long travelId) {
        List<Day> days = dayRepo.findAllByTravelPlanId(travelId);
        days.sort(null);
        return days;
    }

    public List<LocationTime> getLocationTimesByDayId(long dayId) {
        return locationTimeRepo.findByDayId(dayId);
    }

    public List<List<LocationTime>> getLocationTimesByDaysIn(List<Day> days) {
        return days.stream().map(Day::getId).map(l -> locationTimeRepo.findByDayId(l)).collect(Collectors.toList());
    }

    public Optional<City> getCityById(String id) {
        return cityRepo.findById(id);
    }

}
