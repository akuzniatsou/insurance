package com.dww.insurance.domain;

import java.util.Arrays;
import java.util.Objects;

public class Credentials {
    private String user;
    private char[] password;

    public String getUser() {
        return user;
    }

    public char[] getPassword() {
        return password;
    }

    public Credentials(String user, char[] password) {
        this.user = user;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Credentials that = (Credentials) o;
        return Objects.equals(user, that.user) &&
            Arrays.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(user);
        result = 31 * result + Arrays.hashCode(password);
        return result;
    }
}
