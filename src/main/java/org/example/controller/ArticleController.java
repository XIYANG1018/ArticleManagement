package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.pojo.Result;
import org.example.utils.JwtUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @GetMapping("/list")
    public Result<String> list() {
//        // verify token before providing service
//        try {
//            Map<String, Object> claims = JwtUtil.parseToken(token);
//            return Result.success("All articles data");
//        } catch (Exception e) {
//            // http status code 401
//            response.setStatus(401);
//            return Result.error("Haven't login");
//        }

        return Result.success("All articles data");



    }
}
