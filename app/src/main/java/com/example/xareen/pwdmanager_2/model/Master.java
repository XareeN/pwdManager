package com.example.xareen.pwdmanager_2.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Master extends RealmObject {
    @PrimaryKey
    private int master_id;
    private String master_pwd;
    private String master_hint;

    public int getMaster_id() {
        return master_id;
    }

    public void setMaster_id(int master_id) {
        this.master_id = master_id;
    }

    public String getMaster_pwd() {
        return master_pwd;
    }

    public void setMaster_pwd(String master_pwd) {
        this.master_pwd = master_pwd;
    }

    public String getMaster_hint() {
        return master_hint;
    }

    public void setMaster_hint(String master_hint) {
        this.master_hint = master_hint;
    }
}
