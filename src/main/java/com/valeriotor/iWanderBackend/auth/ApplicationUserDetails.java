package com.valeriotor.iWanderBackend.auth;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Collection;

@Entity
public class ApplicationUserDetails implements UserDetails {
    @Id
    private final String username;
    private final String password;
    //private final Collection<? extends GrantedAuthority> authorities;
    @Transient private final Collection<? extends GrantedAuthority> authorities = ImmutableList.of();
    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;

    public ApplicationUserDetails() {
        this("", "", ImmutableList.of(), true, true, true, true);
    }

    public ApplicationUserDetails(String username,
                                  String password,
                                  Collection<? extends GrantedAuthority> authorities,
                                  boolean accountNonExpired,
                                  boolean accountNonLocked,
                                  boolean credentialsNonExpired,
                                  boolean enabled) {
        this.username = username;
        this.password = password;
        //this.authorities = authorities;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
