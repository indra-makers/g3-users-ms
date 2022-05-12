package com.co.indra.coinmarketcap.users.model;

import java.io.Serializable;

public class Membership_type implements Serializable {
    private Long id_membership;
    private Boolean premium;
    private Boolean basic;
    private Boolean free;

    public Long getId_membership() {
        return id_membership;
    }

    public void setId_membership(Long id_membership) {
        this.id_membership = id_membership;
    }

    public Boolean getPremium() {
        return premium;
    }

    public void setPremium(Boolean premium) {
        this.premium = premium;
    }

    public Boolean getBasic() {
        return basic;
    }

    public void setBasic(Boolean basic) {
        this.basic = basic;
    }

    public Boolean getFree() {
        return free;
    }

    public void setFree(Boolean free) {
        this.free = free;
    }

    public Membership_type(Long id_membership, Boolean premium, Boolean basic, Boolean free) {
        this.id_membership = id_membership;
        this.premium = premium;
        this.basic = basic;
        this.free = free;
    }
}