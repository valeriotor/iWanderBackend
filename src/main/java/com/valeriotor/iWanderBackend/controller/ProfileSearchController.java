package com.valeriotor.iWanderBackend.controller;

import com.valeriotor.iWanderBackend.auth.ApplicationUserDao;
import com.valeriotor.iWanderBackend.model.dto.UserMinimumDTO;
import com.valeriotor.iWanderBackend.util.IntRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProfileSearchController {

    @Autowired
    private ApplicationUserDao applicationUserDao;

    @RequestMapping("/findUser")
    public List<UserMinimumDTO> getProfilesByPrefix(String prefix, int start, int end) {
        IntRange range = IntRange.of(start, end);
        if(range == null) return new ArrayList<>();
        List<UserMinimumDTO> users = applicationUserDao.findUsersByPrefix(prefix, range);
        return users;
    }
}
