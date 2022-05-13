package com.co.indra.coinmarketcap.users.model;

import java.io.Serializable;

public class MembershipType implements Serializable {
    private Long idMembership;
    private String type;

    public Long getIdMembership() {
        return idMembership;
    }

    public void setIdMembership(Long idMembership) {
        this.idMembership = idMembership;
    }

    public String getType() {
        return type;
    }

    public MembershipType() {
    }

    public void setType(String type) {
        this.type = type;
    }

    public MembershipType(Long idMembership, String type) {
        this.idMembership = idMembership;
        this.type = type;
    }
}