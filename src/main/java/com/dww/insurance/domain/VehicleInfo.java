package com.dww.insurance.domain;

import java.util.Objects;
import java.util.StringJoiner;

public class VehicleInfo {

    private int id;
    private int owner_id;
    private String model;
    private VehicleType type;
    private String number;
    private String bodyId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public String getBodyId() {
        return bodyId;
    }

    public void setBodyId(String bodyId) {
        this.bodyId = bodyId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VehicleInfo that = (VehicleInfo) o;
        return id == that.id &&
            owner_id == that.owner_id &&
            Objects.equals(model, that.model) &&
            type == that.type &&
            Objects.equals(number, that.number) &&
            Objects.equals(bodyId, that.bodyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, owner_id, model, type, number, bodyId);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", VehicleInfo.class.getSimpleName() + "[", "]")
            .add("id=" + id)
            .add("owner_id=" + owner_id)
            .add("model='" + model + "'")
            .add("type=" + type)
            .add("number='" + number + "'")
            .add("bodyId='" + bodyId + "'")
            .toString();
    }
}
