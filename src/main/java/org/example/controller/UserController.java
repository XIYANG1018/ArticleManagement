package org.example.controller;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.validation.constraints.Pattern;
import org.example.pojo.Result;
import org.example.pojo.User;
import org.example.service.impl.UserService;
import org.example.utils.JwtUtil;
import org.example.utils.Md5Util;
import org.example.utils.ThreadLocalUtil;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController // return json data instead of html page
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

    @GetMapping("/userInfo")
    public Result<User> userInfo(/*@RequestHeader(name = "Authorization") String token*/) {
//        // search user by name
//        Map<String, Object> map = JwtUtil.parseToken(token);
//        String username = (String) map.get("username");
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");

        User user = userService.findByUserName(username);
        return Result.success(user);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user) {
        // call service methods
        userService.update(user);
        return Result.success();

    }

    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl) {
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    @PatchMapping("/updatePwd")
    public Result updatePassword(@RequestBody Map<String, String> params) {
        // verify parameters
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");

        // call StringUtils verify params
        if (!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)) {
            return Result.error("Missing required parameters");
        }

        // verify old pwd
        // 1. find user by username
        // 2. compare two pwds
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User loginUser = userService.findByUserName(username);

        if (!loginUser.getPassword().equals(Md5Util.getMD5String(oldPwd))) {
            return Result.error("Wrong old password");
        }

        if (!rePwd.equals(newPwd)) {
            return Result.error("Two new password are not compatible");
        }

        // call service method
        userService.updatePassword(newPwd);
        return Result.success();

    }
}
