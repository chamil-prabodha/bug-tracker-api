package com.chamil.bugtrackerapi.dao;

import com.chamil.bugtrackerapi.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findCommentByIssueIdAndId(Long bugId, Long commentId);
}
