package com.valeriotor.iWanderBackend.datahandler;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.valeriotor.iWanderBackend.datahandler.images.ImageLocationDAO;
import com.valeriotor.iWanderBackend.datahandler.repos.CityRepo;
import com.valeriotor.iWanderBackend.datahandler.repos.DayRepo;
import com.valeriotor.iWanderBackend.datahandler.repos.LocationTimeRepo;
import com.valeriotor.iWanderBackend.datahandler.repos.TravelPlanRepo;
import com.valeriotor.iWanderBackend.model.core.*;
import com.valeriotor.iWanderBackend.model.dto.LocationTimeDTO;
import com.valeriotor.iWanderBackend.model.dto.TravelPlanDTO;
import com.valeriotor.iWanderBackend.model.dto.TravelPlanMinimumDTO;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class TravelPlanDataHandler {
    private final Mapper mapper;
    private final TravelPlanRepo planRepo;
    private final DayRepo dayRepo;
    private final CityRepo cityRepo;
    private final LocationTimeRepo locationTimeRepo;
    private final ImageLocationDAO imageLocationDAO;

    @Autowired
    public TravelPlanDataHandler(Mapper mapper, TravelPlanRepo planRepo, DayRepo dayRepo, CityRepo cityRepo, LocationTimeRepo locationTimeRepo, ImageLocationDAO imageLocationDAO) {
        this.mapper = mapper;
        this.planRepo = planRepo;
        this.dayRepo = dayRepo;
        this.cityRepo = cityRepo;
        this.locationTimeRepo = locationTimeRepo;
        this.imageLocationDAO = imageLocationDAO;
    }

    public void addTravel(TravelPlanDTO planDTO) {
        TravelPlan travelPlan = mapper.map(planDTO, TravelPlan.class);
        travelPlan.setId(0);
        travelPlan.setUser(AuthUtil.getPrincipal());
        planRepo.save(travelPlan);
    }

    public int renameTravel(long travelId, String newName) {
        if(ownsTravel(travelId))
            return planRepo.setNameForTravel(travelId, newName);
        return 0;
    }

    public void deleteTravel(long travelId) {
        if(ownsTravel(travelId))
            planRepo.deleteById(travelId);
    }

    public void updateTravel(TravelPlanDTO updatedPlanDTO) {
        TravelPlan travelPlan = mapper.map(updatedPlanDTO, TravelPlan.class);
        travelPlan.setUser(AuthUtil.getPrincipal());
        updateTravel(travelPlan);
    }

    public void updateTravel(TravelPlan updatedPlan) {
        if(ownsTravel(updatedPlan.getId())) {
            planRepo.deleteById(updatedPlan.getId());
            planRepo.flush();
            planRepo.save(updatedPlan);
        }
    }

    private boolean ownsTravel(long travelId) {
        AppUser user = AuthUtil.getPrincipal();
        Optional<TravelPlan> optionalPlan = planRepo.findById(travelId);
        if(optionalPlan.isEmpty()) throw new IllegalArgumentException();
        TravelPlan oldPlan = optionalPlan.get();
        return oldPlan.getUser().getUsername().equals(user.getUsername());
    }

    public List<TravelPlanMinimumDTO> getTravelsForUser(String username, Pageable pageable) {
        List<TravelPlanMinimumDTO> plans = planRepo.findAllByUser_usernameIn(ImmutableList.of(username), pageable);
        return plans;
    }

    public TravelPlanMinimumDTO getTravel(long travelId) {
        return planRepo.findById(travelId, TravelPlanMinimumDTO.class);
    }

    public Optional<TravelPlan> getTravelPlan(long travelId) {
        Optional<TravelPlan> byId = planRepo.findById(travelId);
        byId.ifPresent(t -> t.getDays().size());
        return byId;
    }

    public List<Day> getDaysByTravelId(long travelId, Pageable pageable) {
        List<Day> days = dayRepo.findAllByTravelPlan_Id(travelId, pageable);
        return days;
    }

    public List<LocationTimeDTO> getLocationTimesForDayAtIndex(long travelId, int dayIndex, Pageable pageable) {
        List<Day> days = dayRepo.findAllByTravelPlan_Id(travelId, PageRequest.of(0, Integer.MAX_VALUE, Sort.by("date")));
        if(days.size() <= dayIndex || dayIndex < 0)
            return new ArrayList<>();
        long dayId = days.get(dayIndex).getId();
        List<LocationTime> locationTimes = locationTimeRepo.findAllByDay_Id(dayId, pageable);
        List<LocationTimeDTO> locationTimeDTOS = convertLocationTimesToDTOs(locationTimes);
        return locationTimeDTOS;
    }

    public List<LocationTimeDTO> getLocationTimesByDayId(long dayId, Pageable pageable) {
        List<LocationTime> locationTimes = locationTimeRepo.findAllByDay_Id(dayId, pageable);
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


    @Transactional
    public void addImageToTravel(long travelId, byte[] bytes) {
        AppUser user = AuthUtil.getPrincipal();
        String imageURL = imageLocationDAO.saveImageAndGetURL(bytes, user);
        Optional<TravelPlan> travelPlan = planRepo.findById(travelId);
        travelPlan.ifPresent(plan -> plan.addImageUrl(imageURL));
        planRepo.flush();
    }


    @Transactional
    public void removeImageFromTravel(long travelId, String imageUrl) {
        AppUser user = AuthUtil.getPrincipal();
        Optional<TravelPlan> plan = planRepo.findById(travelId);
        if(plan.isPresent() && plan.get().getUser().getUsername().equals(user.getUsername())) {
            if(plan.get().getImageUrls().contains(imageUrl)) {
                imageLocationDAO.deleteImageByURL(imageUrl);
                plan.get().removeImageUrl(imageUrl);
            }
        }
    }

    public List<String> getImageUrls(long travelId) {
        Optional<TravelPlan> travelPlan = planRepo.findById(travelId);
        if(travelPlan.isPresent()) {
            return travelPlan.get().getImageUrls();
        }
        return Lists.newArrayList();
    }

}
