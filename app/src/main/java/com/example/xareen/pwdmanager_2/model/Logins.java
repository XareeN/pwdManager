package com.example.xareen.pwdmanager_2.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Logins extends RealmObject {
    @PrimaryKey
    private long id;
    private String title;
    private String username;
    private String pwd;
    private String pwd2;
    private String email;
    private String website;
    private String notes;
    private String when_created;
    private String last_mod;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPwd2() {
        return pwd2;
    }

    public void setPwd2(String pwd2) {
        this.pwd2 = pwd2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getWhen_created() {
        return when_created;
    }

    public void setWhen_created(String when_created) {
        this.when_created = when_created;
    }

    public String getLast_mod() {
        return last_mod;
    }

    public void setLast_mod(String last_mod) {
        this.last_mod = last_mod;
    }
}
