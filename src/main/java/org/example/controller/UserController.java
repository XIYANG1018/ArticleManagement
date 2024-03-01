package org.example.controller;

import org.example.pojo.Result;
import org.example.pojo.User;
import org.example.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user") // Request Mapping Path
public class UserController {

    // automatically inject the UserService object, which is responsible for handling business logic related to users.
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(String username, String password) {
        // Check if the name is registered
        User u = userService.findByUserName(username);

        if (u == null) {
            // not exist
            // register
            userService.register(username, password);
            return Result.success();
        } else {
            // exist
            return Result.error("This username has been registered");
        }

    }
}
