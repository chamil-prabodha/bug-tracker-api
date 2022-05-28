package com.chamil.bugtrackerapi.service;

import com.chamil.bugtrackerapi.dao.BugRepository;
import com.chamil.bugtrackerapi.dao.CommentRepository;
import com.chamil.bugtrackerapi.exception.APIException;
import com.chamil.bugtrackerapi.exception.ErrorCode;
import com.chamil.bugtrackerapi.model.entity.Bug;
import com.chamil.bugtrackerapi.model.entity.Comment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Slf4j
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final BugRepository bugRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, BugRepository bugRepository) {
        this.commentRepository = commentRepository;
        this.bugRepository = bugRepository;
    }

    public Comment get(Long bugId, Long commentId) throws APIException {
        try {
            return commentRepository.findCommentByIssueIdAndId(bugId, commentId).orElseThrow();
        } catch (NoSuchElementException e) {
            log.warn("Requested comment not found for bug id [{}] comment id [{}]", bugId, commentId, e);
            throw new APIException(ErrorCode.NOT_FOUND, String.format("Requested comment not found for bug id [%s] comment id [%s]", bugId, commentId));
        } catch (Exception e) {
            log.error("An unknown exception occurred while retrieving the comment for bug id [{}] comment id [{}]", bugId, commentId, e);
            throw new APIException(ErrorCode.UNKNOWN_EXCEPTION, e.getMessage());
        }
    }

    public Comment create(Long bugId, Comment comment) throws APIException {
        try {
            Bug bug = bugRepository.findById(bugId).orElseThrow();
            comment.setIssueId(bug.getId());
            return commentRepository.save(comment);
        } catch (NoSuchElementException e) {
            log.warn("Requested bug was not found for bug id [{}]", bugId, e);
            throw new APIException(ErrorCode.NOT_FOUND, String.format("Requested bug was not found for bug id [%s]", bugId));
        } catch (Exception e) {
            log.error("An unknown exception occurred while creating comment [{}] for bug id [{}] ", comment, bugId, e);
            throw new APIException(ErrorCode.UNKNOWN_EXCEPTION, e.getMessage());
        }
    }

    public Comment update(Long bugId, Long commentId, Comment comment) throws APIException {
        try {
            Comment existingComment = commentRepository.findCommentByIssueIdAndId(bugId, commentId).orElseThrow();
            existingComment = existingComment.merge(comment);
            return commentRepository.save(existingComment);
        } catch (NoSuchElementException e) {
            log.warn("Requested comment was not found for bug id [{}] comment id [{}]", bugId, commentId, e);
            throw new APIException(ErrorCode.NOT_FOUND, String.format("Requested comment was not found for bug id [%s] comment id [%s]", bugId, commentId));
        } catch (Exception e) {
            log.error("An unknown exception occurred while updating comment id [{}] for bug id [{}] ", commentId, bugId, e);
            throw new APIException(ErrorCode.UNKNOWN_EXCEPTION, e.getMessage());
        }
    }

    public Comment delete(Long bugId, Long commentId) throws APIException {
        try {
            Comment existingComment = commentRepository.findCommentByIssueIdAndId(bugId, commentId).orElseThrow();
            commentRepository.delete(existingComment);
            return existingComment;
        } catch (NoSuchElementException e) {
            log.warn("Requested comment was not found for bug id [{}] comment id [{}]", bugId, commentId, e);
            throw new APIException(ErrorCode.NOT_FOUND, String.format("Requested comment was not found for bug id [%s] comment id [%s]", bugId, commentId));
        } catch (Exception e) {
            log.error("An unknown exception occurred while updating comment id [{}] for bug id [{}] ", commentId, bugId, e);
            throw new APIException(ErrorCode.UNKNOWN_EXCEPTION, e.getMessage());
        }
    }
}
