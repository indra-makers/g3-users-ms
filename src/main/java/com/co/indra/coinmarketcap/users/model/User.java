package com.co.indra.coinmarketcap.users.model;

import java.io.Serializable;

public class User implements Serializable {
    private Long user_id;
    private String name;
    private String mail;

    public User(long id, String name, String mail) {
        this.user_id = id;
        this.name = name;
        this.mail = mail;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
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
}
