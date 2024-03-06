package org.example.service.impl;

import org.example.mapper.ArticleMapper;
import org.example.pojo.Article;
import org.example.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService{

    @Autowired
    private ArticleMapper articleMapper;
    @Override
    public void add(Article article) {
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());

        // get the current user id
        Map<String, Object> map = ThreadLocalUtil.get();
        int userId = (int) map.get("id");
        article.setCreateUser(userId);

        articleMapper.add(article);
    }
}