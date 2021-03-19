package com.valeriotor.iWanderBackend.datahandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class DataHandlers {



    @Autowired
    private ProfileDataHandler profileDataHandler;
    @Autowired
    private TravelPlanDataHandler travelPlanDataHandler;

    public ProfileDataHandler getProfileDataHandler() {
        return profileDataHandler;
    }

    public TravelPlanDataHandler getTravelPlanDataHandler() {
        return travelPlanDataHandler;
    }
}
