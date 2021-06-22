package com.valeriotor.iWanderBackend.datahandler;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.valeriotor.iWanderBackend.datahandler.images.ImageLocationDAO;
import com.valeriotor.iWanderBackend.datahandler.repos.*;
import com.valeriotor.iWanderBackend.model.core.*;
import com.valeriotor.iWanderBackend.model.dto.*;
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
    private final DayCommentsRepo dayCommentsRepo;
    private final ImageLocationDAO imageLocationDAO;

    @Autowired
    public TravelPlanDataHandler(Mapper mapper, TravelPlanRepo planRepo, DayRepo dayRepo, CityRepo cityRepo, LocationTimeRepo locationTimeRepo, DayCommentsRepo dayCommentsRepo, ImageLocationDAO imageLocationDAO) {
        this.mapper = mapper;
        this.planRepo = planRepo;
        this.dayRepo = dayRepo;
        this.cityRepo = cityRepo;
        this.locationTimeRepo = locationTimeRepo;
        this.dayCommentsRepo = dayCommentsRepo;
        this.imageLocationDAO = imageLocationDAO;
    }

    public long addTravel(TravelPlanDTO planDTO) {
        TravelPlan travelPlan = mapper.map(planDTO, TravelPlan.class);
        travelPlan.setId(0);
        travelPlan.setUser(AuthUtil.getPrincipal());
        planRepo.save(travelPlan);
        return travelPlan.getId();
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

    public long updateTravel(TravelPlanDTO updatedPlanDTO) {
        TravelPlan travelPlan = mapper.map(updatedPlanDTO, TravelPlan.class);
        travelPlan.setUser(AuthUtil.getPrincipal());
        long newId = updateTravel(travelPlan);
        return newId;
    }

    public long updateTravel(TravelPlan updatedPlan) {
        if(ownsTravel(updatedPlan.getId())) {
            planRepo.deleteById(updatedPlan.getId());
            planRepo.flush();
            long id = planRepo.save(updatedPlan).getId();
            return id;
        }
        return -1;
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

    public int getNumberOfDaysByTravelId(long travelId) {
        Optional<TravelPlan> byId = planRepo.findById(travelId);
        return byId.map(travelPlan -> travelPlan.getDays().size()).orElse(0);
    }

    public List<Day> getDaysByTravelId(long travelId, Pageable pageable) {
        List<Day> days = dayRepo.findAllByTravelPlan_Id(travelId, pageable);
        return days;
    }

    public List<List<LocationTimeDTO>> getLocationTimesForTravel(long travelId) {
        List<Day> days = dayRepo.findAllByTravelPlan_Id(travelId, PageRequest.of(0, Integer.MAX_VALUE, Sort.by("date")));
        List<List<LocationTimeDTO>> lists = new ArrayList<>();
        for(Day d : days) {
            DayDTO dayDTO = mapper.map(d, DayDTO.class);
            lists.add(dayDTO.getLocationTimes());
        }
        return lists;
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

    @Transactional
    public void setMainTravelImage(long travelId, byte[] bytes) {
        AppUser user = AuthUtil.getPrincipal();
        String imageURL = imageLocationDAO.saveImageAndGetURL(bytes, user);
        Optional<TravelPlan> travelPlan = planRepo.findById(travelId);
        travelPlan.ifPresent(plan -> plan.setMainImageUrl(imageURL));
        planRepo.flush();
    }

    public List<String> getImageUrls(long travelId) {
        Optional<TravelPlan> travelPlan = planRepo.findById(travelId);
        if(travelPlan.isPresent()) {
            return travelPlan.get().getImageUrls();
        }
        return Lists.newArrayList();
    }

    public List<CommentDTO> getDayComments(long travelId, int dayIndex, Pageable pageable) {
        List<Day> days = dayRepo.findAllByTravelPlan_Id(travelId, PageRequest.of(0, Integer.MAX_VALUE, Sort.by("date")));
        if(days.size() <= dayIndex || dayIndex < 0)
            return new ArrayList<>();
        List<CommentDTO> comments = dayCommentsRepo.findByDay_Id(days.get(dayIndex).getId(), pageable);
        return comments;
    }

    public List<CommentDTO> getTravelComments(long travelId, Pageable pageable) {
        Optional<TravelPlan> optional = planRepo.findById(travelId);
        if(optional.isPresent()) {
            TravelPlan plan = optional.get();
            List<Long> days = plan.getDays().stream().map(Day::getId).collect(Collectors.toList());
            return dayCommentsRepo.findAllByDay_IdIn(days, pageable);
        }
        return new ArrayList<>();
    }

    public List<DayRouteDTO> getTravelRoutes(long travelId) {
        List<Day> days = dayRepo.findAllByTravelPlan_Id(travelId, PageRequest.of(0, Integer.MAX_VALUE, Sort.by("date")));
        List<DayRouteDTO> routes = new ArrayList<>();
        for(Day d : days) {
            DayRoute dayRoute = d.getRoute();
            if(dayRoute != null) {
                DayRouteDTO dayRouteDTO = mapper.map(dayRoute, DayRouteDTO.class);
                routes.add(dayRouteDTO);
            } else {
                routes.add(new DayRouteDTO());
            }
        }
        return routes;
    }

    //public void setComments(long travelId, List<CommentDTO> comments) {
    //    List<Day> days = dayRepo.findAllByTravelPlan_Id(travelId, PageRequest.of(0, Integer.MAX_VALUE, Sort.by("date")));
    //    for (Day d : days) {
    //        d.getComments().clear();
    //    }
    //    for (CommentDTO c : comments) {
    //        if(c.getDayIndex() >= 0 && c.getDayIndex() < days.size())
    //            days.get(c.getDayIndex()).getComments().add(c.getText());
    //    }
    //    for (Day d: days) {
    //        dayRepo.save(d);
    //    }
    //}

    public void setComments(long travelId, int dayIndex, List<CommentDTO> comments) {
        List<Day> days = dayRepo.findAllByTravelPlan_Id(travelId, PageRequest.of(0, Integer.MAX_VALUE, Sort.by("date")));
        if(days.size() <= dayIndex || dayIndex < 0)
            return;
        Day d = days.get(dayIndex);
        dayCommentsRepo.deleteCommentsWithDayId(d);
        List<DayComment> dayComments = new ArrayList<>();
        for (CommentDTO c :
                comments) {
            DayComment dayComment = mapper.map(c, DayComment.class);
            dayComment.setDay(d);
            dayComments.add(dayComment);
        }
        d.getComments().addAll(dayComments);
        dayRepo.save(d);
    }

}
