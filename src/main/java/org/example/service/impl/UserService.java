package org.example.service.impl;

import org.example.pojo.User;

public interface UserService {

    // Find the user using username
    User findByUserName(String username);

    // Register the user
    void register(String username, String password);
}
