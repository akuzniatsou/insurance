package com.dww.insurance.dto;

public class SearchResult {
    private int personId;
    private String name;
    private String lastName;
    private String bodyNumber;

    public SearchResult(int personId, String name, String lastName, String bodyNumber) {
        this.personId = personId;
        this.name = name;
        this.lastName = lastName;
        this.bodyNumber = bodyNumber;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public int getPersonId() {
        return personId;
    }

    public String getBodyNumber() {
        return bodyNumber;
    }

    @Override
    public String toString() {
        return String.format("Person ID : %20d | Person name : %20s | Person surname : %20s | Body ID : %20s", personId, name, lastName, bodyNumber);
    }
}
