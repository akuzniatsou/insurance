package com.dww.insurance.domain;

public class Person {
    private int personId;
    private String name;
    private String passId;

    public Person(int personId, String name, String passId) {
        this.personId = personId;
        this.name = name;
        this.passId = passId;
    }

    public String getName() {
        return name;
    }

    public int getPersonId() {
        return personId;
    }

    @Override
    public String toString() {
        return name;
    }
}
