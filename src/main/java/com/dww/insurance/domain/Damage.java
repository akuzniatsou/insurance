package com.dww.insurance.domain;


public class Damage {

    private boolean[] damageZone;

    public Damage(boolean[] damageZone) {
        this.damageZone = damageZone;
    }

    public boolean[] getDamageZone() {
        return damageZone;
    }

    public void setDamageZone(boolean[] damageZone) {
        this.damageZone = damageZone;
    }
}
