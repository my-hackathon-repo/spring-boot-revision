package com.dreamweaver.spring_revision.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Article {
    private int id;
    private String title;

    List<Comment> commentList;

    public Article(int id, String title) {
        this.id = id;
        this.title = title;
    }
}
