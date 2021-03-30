package com.valeriotor.iWanderBackend.model.core;

import com.google.common.collect.ImmutableList;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class ApplicationUserDetails implements UserDetails{
    @Id
    private String username;
    private String password;
    //private final Collection<? extends GrantedAuthority> authorities;
    @Transient private final Collection<? extends GrantedAuthority> authorities = ImmutableList.of();
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TravelPlan> plans;

    public ApplicationUserDetails() {
        this("", "", ImmutableList.of(), true, true, true, true);
    }

    public ApplicationUserDetails(String username, String password) {
        this(username, password, ImmutableList.of(), true, true, true, true);
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
        plans = new ArrayList<>();
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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<TravelPlan> getPlans() {
        return plans;
    }

    public void setPlans(List<TravelPlan> plans) {
        this.plans = plans;
    }
}
