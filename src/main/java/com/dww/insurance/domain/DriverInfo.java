package com.dww.insurance.domain;

import java.util.Objects;
import java.util.StringJoiner;

public class DriverInfo {

    private int id;
    private String surname;
    private String name;
    private String lastName;
    private String passId;
    private String address;
    private String phone;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassId() {
        return passId;
    }

    public void setPassId(String passId) {
        this.passId = passId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DriverInfo that = (DriverInfo) o;
        return id == that.id &&
            Objects.equals(surname, that.surname) &&
            Objects.equals(name, that.name) &&
            Objects.equals(lastName, that.lastName) &&
            Objects.equals(passId, that.passId) &&
            Objects.equals(address, that.address) &&
            Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, surname, name, lastName, passId, address, phone);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DriverInfo.class.getSimpleName() + "[", "]")
            .add("id=" + id)
            .add("surname='" + surname + "'")
            .add("name='" + name + "'")
            .add("lastName='" + lastName + "'")
            .add("passId='" + passId + "'")
            .add("address='" + address + "'")
            .add("phone='" + phone + "'")
            .toString();
    }
}
