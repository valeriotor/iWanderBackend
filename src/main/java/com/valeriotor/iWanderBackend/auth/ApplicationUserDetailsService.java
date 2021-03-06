package com.valeriotor.iWanderBackend.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

    private final ApplicationUserDAO dao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationUserDetailsService(PasswordEncoder passwordEncoder, @Qualifier("datahandler") ApplicationUserDAO dao) {
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
