package com.valeriotor.iWanderBackend.controller;

import com.valeriotor.iWanderBackend.datahandler.DataHandlers;
import com.valeriotor.iWanderBackend.model.traveldata.TravelPlanRedux;
import com.valeriotor.iWanderBackend.util.GSONUtil;
import com.valeriotor.iWanderBackend.util.IntRange;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TravelViewController {

    @RequestMapping("/getTravel")
    public String getTravelsByUserID(long userId, int start, int end) {
        List<TravelPlanRedux> planReduxList = DataHandlers.getTravelPlanDataHandler().getTravelsForUser(userId, IntRange.of(start, end));
        return GSONUtil.getGson().toJson(planReduxList);
    }


}
