package com.dww.insurance.domain;

import java.util.Objects;

public class User {
    private String login;
    private String password;
    private UserRole role = UserRole.UNAUTHORIZED;

    public User() {
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User that = (User) o;
        return Objects.equals(login, that.login) &&
                Objects.equals(password, that.password) &&
                role == that.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, role);
    }
}
