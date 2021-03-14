package com.valeriotor.iWanderBackend.model.userdata;

import com.valeriotor.iWanderBackend.model.VisibilityType;
import com.valeriotor.iWanderBackend.model.traveldata.TravelPlan;
import com.valeriotor.iWanderBackend.util.IntRange;

import java.util.List;

public class Profile implements Comparable<Profile>{ // Will require an extended userdata class/set of classes
    private final long userId;
    private final String username;
    private final AccountType associatedAccountType;
    private final String associatedAccountUsername;

    public Profile(long userId, String username, AccountType associatedAccountType, String associatedAccountUsername) {
        this.userId = userId;
        this.username = username;
        this.associatedAccountType = associatedAccountType;
        this.associatedAccountUsername = associatedAccountUsername;
    }

    public long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public AccountType getAssociatedAccountType() {
        return associatedAccountType;
    }

    public String getAssociatedAccountUsername() {
        return associatedAccountUsername;
    }

    public List<TravelPlan> getTravelPlans(IntRange range, VisibilityType visibility) {
        return null;
    }

    public List<Friend> getFriends(IntRange range) {
        return null;
    }

    public List<FriendRequest> getFriendRequests(IntRange range) {
        return null;
    }

    public void askFriendship(long askerId) {

    }

    public void uploadTravelPlan(TravelPlan travelPlan) {

    }

    public void setTravelPlanVisibility(int travelId, VisibilityType visibility) {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Profile profile = (Profile) o;

        return userId == profile.userId;
    }

    @Override
    public int hashCode() {
        return (int) (userId ^ (userId >>> 32));
    }

    @Override
    public int compareTo(Profile o) {
        return Long.compare(userId, o.getUserId());
    }

    @Override
    public String toString() {
        return "Profile{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", associatedAccountType=" + associatedAccountType +
                ", associatedAccountUsername='" + associatedAccountUsername + '\'' +
                '}';
    }

    public ProfileRedux redux() {
        return new ProfileRedux(this);
    }

    public static class ProfileRedux {
        private final long userId;
        private final String username;

        private ProfileRedux(Profile profile) {
            this.userId = profile.getUserId();
            this.username = profile.getUsername();
        }
    }

}
