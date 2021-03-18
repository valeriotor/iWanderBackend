package com.valeriotor.iWanderBackend.datahandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class DataHandlers {

    @Autowired
    private static ProfileDataHandler profileDataHandler;

    private static final TravelPlanDataHandler travelPlanDataHandler = new TravelPlanDataHandler();

    public static ProfileDataHandler getProfileDataHandler() {
        return profileDataHandler;
    }

    public static TravelPlanDataHandler getTravelPlanDataHandler() {
        return travelPlanDataHandler;
    }
}
