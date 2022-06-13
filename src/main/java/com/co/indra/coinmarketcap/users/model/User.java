package com.co.indra.coinmarketcap.users.model;

import java.io.Serializable;

public class User implements Serializable {
    private int userId;
    private String name;
    private String mail;
    private String phone;
    private Long idMembership;

    public User() {
    }

    public User(int userId, String name, String mail, String phone, Long idMembership) {
        this.userId = userId;
        this.name = name;
        this.mail = mail;
        this.phone = phone;
        this.idMembership = idMembership;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getIdMembership() {
        return idMembership;
    }

    public void setIdMembership(Long idMembership) {
        this.idMembership = idMembership;
    }
}
