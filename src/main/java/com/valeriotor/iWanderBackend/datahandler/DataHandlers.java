package com.valeriotor.iWanderBackend.datahandler;

import org.springframework.beans.factory.annotation.Autowired;

public class DataHandlers {

    //@Autowired
    private static final ProfileDataHandler profileDataHandler = new ProfileDataHandler();

    private static final TravelPlanDataHandler travelPlanDataHandler = new TravelPlanDataHandler();

    public static ProfileDataHandler getProfileDataHandler() {
        return profileDataHandler;
    }

    public static TravelPlanDataHandler getTravelPlanDataHandler() {
        return travelPlanDataHandler;
    }
}
