package com.dreamweaver.spring_revision.dto;

import lombok.Data;

import java.util.List;

@Data
public class ArticlePostRequest {
    private Article article;
    private List<Comment> comment;
}
