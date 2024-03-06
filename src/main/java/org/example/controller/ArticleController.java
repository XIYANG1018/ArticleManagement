package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.pojo.Article;
import org.example.pojo.Result;
import org.example.service.impl.ArticleService;
import org.example.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

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

    @PostMapping
    public Result add(@RequestBody @Validated Article article) {
        articleService.add(article);
        return Result.success();

    }

}
