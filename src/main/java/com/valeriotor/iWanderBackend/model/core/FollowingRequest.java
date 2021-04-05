package com.valeriotor.iWanderBackend.model.core;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@IdClass(FollowingRequest.FollowingRequestPK.class)
public class FollowingRequest {
    @Id
    @ManyToOne
    @JoinColumn(name = "asker_username")
    private AppUser asker;
    @Id
    @ManyToOne
    @JoinColumn(name = "target_username")
    private AppUser target;
    private LocalDateTime askDate;

    public FollowingRequest() {
    }

    public FollowingRequest(AppUser asker, AppUser target, LocalDateTime askDate) {
        this.asker = asker;
        this.target = target;
        this.askDate = askDate;
    }

    public AppUser getAsker() {
        return asker;
    }

    public void setAsker(AppUser asker) {
        this.asker = asker;
    }

    public AppUser getTarget() {
        return target;
    }

    public void setTarget(AppUser target) {
        this.target = target;
    }

    public LocalDateTime getAskDate() {
        return askDate;
    }

    public void setAskDate(LocalDateTime askDate) {
        this.askDate = askDate;
    }

    public static class FollowingRequestPK implements Serializable {
        private String asker;
        private String target;

        public FollowingRequestPK() {
        }

        public FollowingRequestPK(String asker, String target) {
            this.asker = asker;
            this.target = target;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            FollowingRequestPK that = (FollowingRequestPK) o;

            if (asker != null ? !asker.equals(that.asker) : that.asker != null) return false;
            return target != null ? target.equals(that.target) : that.target == null;
        }

        @Override
        public int hashCode() {
            int result = asker != null ? asker.hashCode() : 0;
            result = 31 * result + (target != null ? target.hashCode() : 0);
            return result;
        }
    }

}
