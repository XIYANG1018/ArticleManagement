package org.example.service.impl;

import org.example.pojo.User;

public interface UserService {

    // Find the user using username
    User findByUserName(String username);

    // Register the user
    void register(String username, String password);

    // update user info
    void update(User user);

    // update avatar
    void updateAvatar(String avatarUrl);

    // update password
    void updatePassword(String newPwd);
}
