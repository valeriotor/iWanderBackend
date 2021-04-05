package com.valeriotor.iWanderBackend.datahandler;

import com.valeriotor.iWanderBackend.model.TestDataCreator;
import com.valeriotor.iWanderBackend.model.core.AppUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthUtil {

    public static AppUser getPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.getPrincipal() instanceof AppUser) {
            return (AppUser)authentication.getPrincipal();
        } else {
            return TestDataCreator.getADMIN();
        }
    }

}
