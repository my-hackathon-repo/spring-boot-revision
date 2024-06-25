package com.dreamweaver.spring_revision.repo;

import com.dreamweaver.spring_revision.model.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepo extends JpaRepository<ArticleEntity, Integer> {
}
