package com.valeriotor.iWanderBackend.datasource.emulated;

import com.google.common.collect.ImmutableList;
import com.valeriotor.iWanderBackend.datasource.TravelDataSource;
import com.valeriotor.iWanderBackend.model.VisibilityType;
import com.valeriotor.iWanderBackend.model.traveldata.Day;
import com.valeriotor.iWanderBackend.model.traveldata.TravelPlan;
import com.valeriotor.iWanderBackend.model.traveldata.TravelPlanRedux;
import com.valeriotor.iWanderBackend.model.traveldata.temp.IdentitylessTravelPlan;
import com.valeriotor.iWanderBackend.util.IntRange;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class EmulatedTravelDataSource implements TravelDataSource {

    private final Map<Long, Set<TravelPlan>> travelsByUserId = new HashMap<>();

    public EmulatedTravelDataSource() {
        Day d = new Day(0, LocalDate.of(2021, 10, 1), null, new ArrayList<>());
        List<Day> dayz = ImmutableList.of(d);
        addTravel(0, new IdentitylessTravelPlan("Roma", VisibilityType.PUBLIC, dayz));
        addTravel(0, new IdentitylessTravelPlan("Zurigo", VisibilityType.PUBLIC, dayz));
        addTravel(1, new IdentitylessTravelPlan("Parigi", VisibilityType.PUBLIC, dayz));
        addTravel(2, new IdentitylessTravelPlan("Genova", VisibilityType.PUBLIC, dayz));
        addTravel(1, new IdentitylessTravelPlan("Casablanca", VisibilityType.PUBLIC, dayz));
    }

    @Override
    public TravelPlan addTravel(long userId, IdentitylessTravelPlan plan) {
        Set<TravelPlan> plans = getTravelSetForUser(userId);
        Random r = ThreadLocalRandom.current();
        int id = r.nextInt();
        int countCheck = 0;
        while (travelSetContainsId(plans, id)) {
            id = r.nextInt();
            countCheck++;
            if(countCheck > 1000)
                throw new RuntimeException();
        }
        TravelPlan planWithId = plan.create(id);
        plans.add(planWithId);
        return planWithId;
    }

    @Override
    public void updateTravel(long userId, int travelId, TravelPlan travelPlan) {
        Set<TravelPlan> plans = getTravelSetForUser(userId);
        plans.removeIf(plan -> plan.getId() == travelId);
        plans.add(travelPlan);
    }


    @Override
    public List<TravelPlanRedux> getTravelsForUser(long userId, IntRange range) {
        List<TravelPlan> plans = range.getSublist(new ArrayList<>(getTravelSetForUser(userId)));
        return plans.stream().map(TravelPlanRedux::new).collect(Collectors.toList());
    }


    @Override
    public Optional<TravelPlan> getTravel(long userId, int travelId) {
        Set<TravelPlan> plans = getTravelSetForUser(userId);
        return plans.stream().filter(plan -> plan.getId() == travelId).findFirst();
    }


    private Set<TravelPlan> getTravelSetForUser(long userId) {
        Long l = Long.valueOf(userId);
        Set<TravelPlan> plans = travelsByUserId.get(l);
        if(plans == null) {
            plans = new TreeSet<>();
            travelsByUserId.put(l, plans);
        }
        return plans;
    }

    private boolean travelSetContainsId(Set<TravelPlan> plans, int id) {
        for (TravelPlan plan : plans) {
            if(plan.getId() == id) return true;
        }
        return false;
    }


}
