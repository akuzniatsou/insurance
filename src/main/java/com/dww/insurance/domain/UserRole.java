package com.dww.insurance.domain;

import java.util.Arrays;
import java.util.Optional;

public enum UserRole {
    UNAUTHORIZED(0),
    ADMIN(1),
    USER(2),
    ALL(3);

    private int id;

    public int getId() {
        return id;
    }

    UserRole(int id) {
        this.id = id;
    }

    public static UserRole lookupById(int id) {
        Optional<UserRole> role = Arrays.stream(values())
                .filter(userRole -> userRole.id == id)
                .findAny();
        return role.orElseThrow(() -> new IllegalArgumentException("No enum constant with id " + id));
    }
}
