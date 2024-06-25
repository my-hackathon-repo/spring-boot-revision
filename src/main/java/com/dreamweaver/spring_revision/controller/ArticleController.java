package com.dreamweaver.spring_revision.controller;

import com.dreamweaver.spring_revision.dto.Article;
import com.dreamweaver.spring_revision.dto.ArticlePostRequest;
import com.dreamweaver.spring_revision.dto.Comment;
import com.dreamweaver.spring_revision.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping(value = "/articles")
    public List<Article> getArticles() {
        return articleService.getAll();
    }

    @GetMapping(value = "/default")
    public List<Article> getDefaultArticles() {
        articleService.defaultAricles();
        return articleService.getAll();
    }

    @PostMapping(value = "/articles")
    public void postArticles(@RequestBody ArticlePostRequest articlePostRequest) {
         articleService.upsetArticle(articlePostRequest.getArticle(),articlePostRequest.getComment());
    }

}
