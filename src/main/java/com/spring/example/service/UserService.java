package com.spring.example.service;

import com.spring.example.model.User;

public interface UserService {
    User findOne(String username);
    User createUser(User user);
}
