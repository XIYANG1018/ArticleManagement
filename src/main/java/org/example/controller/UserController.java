package org.example.controller;

import jakarta.validation.constraints.Pattern;
import org.example.pojo.Result;
import org.example.pojo.User;
import org.example.service.impl.UserService;
import org.example.utils.JwtUtil;
import org.example.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user") // Request Mapping Path
@Validated
public class UserController {

    // automatically inject the UserService object, which is responsible for handling business logic related to users.
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$")String password) {
        // Request check
//        if (username != null && username.length() >= 5 && username.length() <= 16 &&
//                password != null && password.length() >=5 && password.length() <= 16) {

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
//        } else {
//            return Result.error("Invalid input");
//        }
    }

    @PostMapping("/login")
    // return a String type data, which is the token
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$")String password) {
        // search username
        User loginUser = userService.findByUserName(username);

        if (loginUser == null) {
            return Result.error("No such username");
        }


        // check password
        if (Md5Util.getMD5String(password).equals(loginUser.getPassword())) {
            // Success
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", loginUser.getId());
            claims.put("username", loginUser.getUsername());

            String token = JwtUtil.genToken(claims);

            return Result.success(token);
        }

        return Result.error("Wrong password");
    }
}
