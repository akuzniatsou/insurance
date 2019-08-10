package com.dww.insurance.domain;

import java.sql.Date;

public class DamageInfo {

    private int id;
    private Date date;
    private boolean[] damageZone;

    public DamageInfo(boolean[] damageZone) {
        this.damageZone = damageZone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean[] getDamageZone() {
        return damageZone;
    }

    public void setDamageZone(boolean[] damageZone) {
        this.damageZone = damageZone;
    }

}
