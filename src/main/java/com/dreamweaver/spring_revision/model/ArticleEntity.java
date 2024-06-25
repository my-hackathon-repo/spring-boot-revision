package com.dreamweaver.spring_revision.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
// Adding the table name
@Table(name = "Articles")
@Data
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;
    public ArticleEntity() {}

    @OneToMany(cascade = CascadeType.ALL)
    private Set<CommentsEntity> commentsEntities;
    public ArticleEntity(Integer id, String title)
    {
        this.id = id;
        this.title = title;
    }
    public ArticleEntity(String title)
    {
        this.title = title;
    }
}