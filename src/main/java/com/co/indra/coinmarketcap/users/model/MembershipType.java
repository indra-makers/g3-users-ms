package com.co.indra.coinmarketcap.users.model;

import java.io.Serializable;

public class MembershipType implements Serializable {
    private Long membership_id;
    private String type;

    public Long getMembership_id() {
        return membership_id;
    }

    public void setMembership_id(Long membership_id) {
        this.membership_id = membership_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public MembershipType() {
        this.membership_id = membership_id;
        this.type = type;
    }
}