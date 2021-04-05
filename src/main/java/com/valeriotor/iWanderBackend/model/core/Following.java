package com.valeriotor.iWanderBackend.model.core;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@IdClass(Following.FollowingPK.class)
public class Following {
    @Id
    @ManyToOne
    @JoinColumn(name = "follower_username")
    private AppUser follower;
    @Id
    @ManyToOne
    @JoinColumn(name = "followee_username")
    private AppUser followee;
    private LocalDateTime followDate;

    public Following() {
    }

    public Following(AppUser follower, AppUser followee, LocalDateTime followDate) {
        this.follower = follower;
        this.followee = followee;
        this.followDate = followDate;
    }

    public AppUser getFollower() {
        return follower;
    }

    public void setFollower(AppUser follower) {
        this.follower = follower;
    }

    public AppUser getFollowee() {
        return followee;
    }

    public void setFollowee(AppUser followee) {
        this.followee = followee;
    }

    public LocalDateTime getFollowDate() {
        return followDate;
    }

    public void setFollowDate(LocalDateTime followDate) {
        this.followDate = followDate;
    }

    public static class FollowingPK implements Serializable {
        private String follower;
        private String followee;

        public FollowingPK() {
        }

        public FollowingPK(String follower, String followee) {
            this.follower = follower;
            this.followee = followee;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            FollowingPK that = (FollowingPK) o;

            if (follower != null ? !follower.equals(that.follower) : that.follower != null) return false;
            return followee != null ? followee.equals(that.followee) : that.followee == null;
        }

        @Override
        public int hashCode() {
            int result = follower != null ? follower.hashCode() : 0;
            result = 31 * result + (followee != null ? followee.hashCode() : 0);
            return result;
        }
    }

}
