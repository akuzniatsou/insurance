package com.dww.insurance.domain;

import java.util.Arrays;
import java.util.Optional;

public enum UserRole {
    UNAUTHORIZED("0"),
    ADMIN("1"),
    USER("2");

    private String i;

    UserRole(String i) {
        this.i = i;
    }

    public static UserRole lookupById(String id) {
        if (id == null) throw new NullPointerException("Id is null");
        Optional<UserRole> role = Arrays.stream(values())
                .filter(userRole -> userRole.i.equals(id))
                .findAny();
        return role.orElseThrow(() -> new IllegalArgumentException("No enum constant with id " + id));

    }
}
