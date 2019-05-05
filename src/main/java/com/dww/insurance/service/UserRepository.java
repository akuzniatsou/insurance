package com.dww.insurance.service;

import com.dww.insurance.domain.Credentials;

public class UserRepository {
    public boolean validUser(Credentials credentials) {
        return new Credentials("user", new char[]{'p','a','s','s'}).equals(credentials);
    }
}
