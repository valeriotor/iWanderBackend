package com.valeriotor.iWanderBackend.model.userdata.temp;

import com.valeriotor.iWanderBackend.model.userdata.AccountType;

public class IdentitylessProfile {
    private final String username;
    private final AccountType associatedAccountType;
    private final String associatedAccountUsername;

    public IdentitylessProfile(String username, AccountType associatedAccountType, String associatedAccountUsername) {
        this.username = username;
        this.associatedAccountType = associatedAccountType;
        this.associatedAccountUsername = associatedAccountUsername;
    }
}
