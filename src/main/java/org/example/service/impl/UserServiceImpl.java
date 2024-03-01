package org.example.service.impl;

import org.example.mapper.UserMapper;
import org.example.pojo.Result;
import org.example.pojo.User;
import org.example.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Implementation of UserService Interface
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User findByUserName(String username) {
        User u = userMapper.findByUserName(username);
        return u;
    }

    @Override
    public void register(String username, String password) {
        // Encryption password, password is not safe if we store the password directly
        String md5String = Md5Util.getMD5String(password);

        // add to the mapper
        userMapper.add(username, md5String);
    }


}
