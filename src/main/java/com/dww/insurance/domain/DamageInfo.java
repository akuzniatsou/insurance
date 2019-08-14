package com.dww.insurance.domain;

import java.sql.Date;
import java.util.Arrays;
import java.util.Objects;
import java.util.StringJoiner;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DamageInfo that = (DamageInfo) o;
        return id == that.id &&
            Objects.equals(date, that.date) &&
            Arrays.equals(damageZone, that.damageZone);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, date);
        result = 31 * result + Arrays.hashCode(damageZone);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DamageInfo.class.getSimpleName() + "[", "]")
            .add("id=" + id)
            .add("date=" + date)
            .add("damageZone=" + Arrays.toString(damageZone))
            .toString();
    }
}
