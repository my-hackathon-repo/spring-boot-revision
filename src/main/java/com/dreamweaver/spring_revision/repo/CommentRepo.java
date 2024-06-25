package com.dreamweaver.spring_revision.repo;

import com.dreamweaver.spring_revision.model.CommentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepo extends JpaRepository<CommentsEntity, Integer> {
}
