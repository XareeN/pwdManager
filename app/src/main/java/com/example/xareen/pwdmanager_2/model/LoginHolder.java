package com.example.xareen.pwdmanager_2.model;

public class LoginHolder {
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

    public LoginHolder(Logins logins) {

        this.id = logins.getId();
        this.title = logins.getTitle();
        this.username = logins.getUsername();
        this.pwd = logins.getPwd();
        this.pwd2 = logins.getPwd2();
        this.email = logins.getEmail();
        this.website = logins.getWebsite();
        this.notes = logins.getNotes();
        this.when_created = logins.getWhen_created();
        this.last_mod = logins.getLast_mod();
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUsername() {
        return username;
    }

    public String getPwd() {
        return pwd;
    }

    public String getPwd2() {
        return pwd2;
    }

    public String getEmail() {
        return email;
    }

    public String getWebsite() {
        return website;
    }

    public String getNotes() {
        return notes;
    }

    public String getWhen_created() {
        return when_created;
    }

    public String getLast_mod() {
        return last_mod;
    }
}
