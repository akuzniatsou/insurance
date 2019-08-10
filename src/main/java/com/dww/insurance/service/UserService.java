package com.dww.insurance.service;

import com.dww.insurance.domain.User;
import com.dww.insurance.repository.UserRepository;

import java.util.List;

public class UserService {

    private UserRepository userRepository;

    public User authorize(User user) {
        return userRepository.authorize(user);
    }

    public List<User> findUsers() {
        return userRepository.findUsers();
    }

    public List<User> find(User user) {
        return userRepository.find(user);
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void deleteUser(String login) {
        userRepository.deleteUser(login);
    }

    public void insertUser(User user) {
        userRepository.insertUser(user);
    }
}

