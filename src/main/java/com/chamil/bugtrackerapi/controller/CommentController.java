package com.chamil.bugtrackerapi.controller;

import com.chamil.bugtrackerapi.exception.APIException;
import com.chamil.bugtrackerapi.model.dto.request.CommentDTO;
import com.chamil.bugtrackerapi.model.dto.response.BugTrackerResponse;
import com.chamil.bugtrackerapi.model.entity.Comment;
import com.chamil.bugtrackerapi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bugs/{bugId}")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/comments/{commentId}")
    public ResponseEntity<BugTrackerResponse<Comment>> getComment(@PathVariable Long bugId, @PathVariable Long commentId) throws APIException {
        Comment comment = commentService.get(bugId, commentId);
        BugTrackerResponse<Comment> apiResponse = new BugTrackerResponse<>(true, comment);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/comments")
    public ResponseEntity<BugTrackerResponse<Comment>> createComment(@PathVariable Long bugId, @RequestBody CommentDTO commentDTO) throws APIException {
        Comment comment = commentService.create(bugId, commentDTO.merge(new Comment()));
        BugTrackerResponse<Comment> apiResponse = new BugTrackerResponse<>(true, comment);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<BugTrackerResponse<Comment>> updateComment(@PathVariable Long bugId, @PathVariable Long commentId, @RequestBody CommentDTO commentDTO) throws APIException {
        Comment comment = commentService.update(bugId, commentId, commentDTO.merge(new Comment()));
        BugTrackerResponse<Comment> apiResponse = new BugTrackerResponse<>(true, comment);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<BugTrackerResponse<Comment>> deleteComment(@PathVariable Long bugId, @PathVariable Long commentId) throws APIException {
        Comment comment = commentService.delete(bugId, commentId);
        BugTrackerResponse<Comment> apiResponse = new BugTrackerResponse<>(true, comment);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
