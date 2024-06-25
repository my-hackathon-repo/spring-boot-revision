package com.dreamweaver.spring_revision.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
// Adding the table name
@Table(name = "Comments")
@Data
public class CommentsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String comment;
    public CommentsEntity() {}

    // Mapping the column of this table
    @ManyToOne
    //Adding the name
    @JoinColumn(name = "Article_id")
    ArticleEntity article;

    public CommentsEntity(Integer id, String title, ArticleEntity article)
    {
        this.id = id;
        this.comment = title;
        this.article = article;
    }

    public CommentsEntity(int id, String title)
    {
        this.id = id;
        this.comment = title;
    }

}