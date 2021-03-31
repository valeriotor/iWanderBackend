package com.valeriotor.iWanderBackend.datahandler;

import com.google.common.collect.ImmutableList;
import com.valeriotor.iWanderBackend.datahandler.repos.CityRepo;
import com.valeriotor.iWanderBackend.datahandler.repos.DayRepo;
import com.valeriotor.iWanderBackend.datahandler.repos.LocationTimeRepo;
import com.valeriotor.iWanderBackend.datahandler.repos.TravelPlanRepo;
import com.valeriotor.iWanderBackend.model.TestDataCreator;
import com.valeriotor.iWanderBackend.model.core.*;
import com.valeriotor.iWanderBackend.model.dto.LocationTimeDTO;
import com.valeriotor.iWanderBackend.model.dto.TravelPlanDTO;
import com.valeriotor.iWanderBackend.model.dto.TravelPlanMinimumDTO;
import com.valeriotor.iWanderBackend.util.IntRange;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private Mapper mapper;
    @Autowired
    private TravelPlanRepo planRepo;
    @Autowired
    private DayRepo dayRepo;
    @Autowired
    private CityRepo cityRepo;
    @Autowired
    private LocationTimeRepo locationTimeRepo;

    public void addTravel(TravelPlanDTO planDTO) {
        TravelPlan travelPlan = mapper.map(planDTO, TravelPlan.class);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.getPrincipal() instanceof ApplicationUserDetails) {
            travelPlan.setUser((ApplicationUserDetails)authentication.getPrincipal());
        } else {
            travelPlan.setUser(TestDataCreator.getADMIN());
        }
        planRepo.save(travelPlan);
    }

    public int renameTravel(long travelId, String newName) {
        return planRepo.setNameForTravel(travelId, newName);
    }

    public void deleteTravel(long travelId) {
        planRepo.deleteById(travelId);
    }

    public void updateTravel(TravelPlan updatedPlan) {
        planRepo.deleteById(updatedPlan.getId());
        planRepo.flush();
        planRepo.save(updatedPlan);
    }

    public List<TravelPlanMinimumDTO> getTravelsForUser(String username, IntRange range) {
        if(range == null) return ImmutableList.of();
        List<TravelPlanMinimumDTO> plans = planRepo.findAllByUser_usernameIn(ImmutableList.of(username));
        plans.sort(null);
        return plans;
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

    public List<LocationTimeDTO> getLocationTimesForDayAtIndex(long travelId, int dayIndex) {
        List<Day> days = dayRepo.findAllByTravelPlan_Id(travelId);
        days.sort(null);
        if(days.size() <= dayIndex || dayIndex < 0)
            return new ArrayList<>();
        long dayId = days.get(dayIndex).getId();
        System.out.println("AAAAAAAAAAAAAAAAAAAA" + dayId);
        List<LocationTime> locationTimes = locationTimeRepo.findAllByDay_Id(dayId);
        List<LocationTimeDTO> locationTimeDTOS = convertLocationTimesToDTOs(locationTimes);
        return locationTimeDTOS;
    }

    public List<LocationTimeDTO> getLocationTimesByDayId(long dayId) {
        List<LocationTime> locationTimes = locationTimeRepo.findAllByDay_Id(dayId);
        List<LocationTimeDTO> locationTimeDTOS = convertLocationTimesToDTOs(locationTimes);
        return locationTimeDTOS;
    }

    private List<LocationTimeDTO> convertLocationTimesToDTOs(List<LocationTime> locationTimes) {
        List<LocationTimeDTO> locationTimeDTOS = new ArrayList<>();
        for(LocationTime lt : locationTimes) {
            locationTimeDTOS.add(mapper.map(lt, LocationTimeDTO.class));
        }
        return locationTimeDTOS;
    }

    public List<List<LocationTime>> getLocationTimesByDaysIn(List<Day> days) {
        return days.stream().map(Day::getLocationTimes).collect(Collectors.toList());
    }

    public Optional<City> getCityById(String id) {
        return cityRepo.findById(id);
    }

}
