package com.valeriotor.iWanderBackend.model.core;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class AppUser implements UserDetails{
    @Id
    private String username;
    private String password;
    private String name;
    private String surname;
    private LocalDate birthDate;
    private String bio;
    private String imageURL;
    private int preferences;
    private String email;
    //private final Collection<? extends GrantedAuthority> authorities;
    @Transient private final Collection<? extends GrantedAuthority> authorities = new ArrayList<>();
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TravelPlan> plans;
    @OneToMany(mappedBy = "followee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Following> followers; // Users who follow this user
    @OneToMany(mappedBy = "follower", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Following> followees;// Users this user follows
    @OneToMany(mappedBy = "target", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FollowingRequest> askers; // Users who asked this user
    @OneToMany(mappedBy = "asker", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FollowingRequest> targets;// Users this user asked to

    public AppUser() {
        this("", "", new ArrayList<>(), true, true, true, true);
    }

    public AppUser(String username, String password) {
        this(username, password, new ArrayList<>(), true, true, true, true);
    }

    public AppUser(String username,
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
        followers = new ArrayList<>();
        followees = new ArrayList<>();
        askers = new ArrayList<>();
        targets = new ArrayList<>();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<Following> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Following> followers) {
        this.followers = followers;
    }

    public List<Following> getFollowees() {
        return followees;
    }

    public void setFollowees(List<Following> followees) {
        this.followees = followees;
    }

    public List<FollowingRequest> getAskers() {
        return askers;
    }

    public void setAskers(List<FollowingRequest> askers) {
        this.askers = askers;
    }

    public List<FollowingRequest> getTargets() {
        return targets;
    }

    public void setTargets(List<FollowingRequest> targets) {
        this.targets = targets;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authorities=" + authorities +
                ", accountNonExpired=" + accountNonExpired +
                ", accountNonLocked=" + accountNonLocked +
                ", credentialsNonExpired=" + credentialsNonExpired +
                ", enabled=" + enabled +
                ", #plans=" + plans.size() +
                '}';
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getPreferences() {
        return preferences;
    }

    public void setPreferences(int preferences) {
        this.preferences = preferences;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
