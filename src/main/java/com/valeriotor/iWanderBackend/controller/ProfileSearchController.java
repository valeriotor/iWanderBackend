package com.valeriotor.iWanderBackend.controller;

import com.valeriotor.iWanderBackend.auth.ApplicationUserDao;
import com.valeriotor.iWanderBackend.model.dto.UserMinimumDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProfileSearchController {

    @Autowired
    private ApplicationUserDao applicationUserDao;

    @RequestMapping("/findUser")
    public List<UserMinimumDTO> getProfilesByPrefix(String prefix, Pageable pageable) {
        Slice<UserMinimumDTO> users = applicationUserDao.findUsersByPrefix(prefix, pageable);
        return users.getContent();
    }
}
