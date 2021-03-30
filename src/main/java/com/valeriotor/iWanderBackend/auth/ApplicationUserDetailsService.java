package com.valeriotor.iWanderBackend.auth;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.valeriotor.iWanderBackend.model.core.ApplicationUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

    private final ApplicationUserDao dao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationUserDetailsService(PasswordEncoder passwordEncoder, @Qualifier("datahandler") ApplicationUserDao dao) {
        this.passwordEncoder = passwordEncoder;
        this.dao = dao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return dao.findUserDetailsByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Couldn't find username: ApplicationUserDetailsService"));
        //return repo.findById(username)
          //      .orElseThrow(() -> new UsernameNotFoundException("Couldn't find username: ApplicationUserDetailsService"));
    }
}
