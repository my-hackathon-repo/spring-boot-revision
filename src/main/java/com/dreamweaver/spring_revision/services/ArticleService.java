package com.dreamweaver.spring_revision.services;

import com.dreamweaver.spring_revision.dto.Article;
import com.dreamweaver.spring_revision.dto.Comment;
import com.dreamweaver.spring_revision.model.ArticleEntity;
import com.dreamweaver.spring_revision.model.CommentsEntity;
import com.dreamweaver.spring_revision.repo.ArticleRepo;
import com.dreamweaver.spring_revision.repo.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    @Autowired
    private  ArticleRepo articleRepo;
    @Autowired
    private  CommentRepo commentRepo;

    public void upsetArticle(Article article, List<Comment> comment) {

        Optional<ArticleEntity> articleEntity =  articleRepo.findById(article.getId());

        if (articleEntity.isEmpty()) {
            // no article, we will add few article with default comments
            ArticleEntity articleEntity1 = new ArticleEntity(article.getId() > 0 ? article.getId(): null, article.getTitle());
            articleRepo.save(articleEntity1);
            commentRepo.saveAll(generateComments(comment, articleEntity1));
        } else {
            ArticleEntity articleEntity1 = articleEntity.get();
            // maniplate existing comment and article
            if (!articleEntity1.getTitle().equals(article.getTitle())) {
                articleEntity1.setTitle(article.getTitle());
                articleRepo.save(articleEntity1);
            }

            final ArticleEntity finalArticleEntity = articleEntity1;

            List<CommentsEntity> commentsEntity = comment.stream().map(x ->
            {
                Optional<CommentsEntity> entity = commentRepo.findById(x.getId());
                if (entity.isPresent()) {
                    CommentsEntity commentsEntity1 = entity.get();
                    commentsEntity1.setComment(x.getComment());
                    return commentsEntity1;
                } else {
                    return getCommentsEntity(finalArticleEntity, x);
                }
            }).toList();

            commentRepo.saveAll(commentsEntity);
        }

    }

    private List<CommentsEntity> generateComments(List<Comment> comment, ArticleEntity article){
        return comment.stream().map(x -> getCommentsEntity(article, x)).collect(Collectors.toList());
    }

    private static CommentsEntity getCommentsEntity(ArticleEntity article, Comment x) {
        return new CommentsEntity(x.getId() > 0 ? x.getId() : null, x.getComment(), article);
    }

    public List<Article> getAll() {
        return (List<Article>) articleRepo.findAll().stream()
                .map(x -> new Article(x.getId(), x.getTitle(), x.getCommentsEntities().stream()
                        .map(y -> new Comment(y.getId(), y.getComment())).toList()));
    }

    public void defaultAricles() {

        for(int i = 0; i < 2; i++) {
                Article article = new Article(0, "Test_" + i);
                List<Comment> commentList = new ArrayList<>();
            for(int j = 0; j < 10; j++) {
                Comment comment = new Comment(0, "Comment_" + i);
                commentList.add(comment);
            }

            this.upsetArticle(article, commentList);
        }

    }
}
