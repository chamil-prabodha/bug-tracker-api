package com.chamil.bugtrackerapi.controller;

import com.chamil.bugtrackerapi.model.dto.request.CommentDTO;
import com.chamil.bugtrackerapi.model.dto.response.BugTrackerResponse;
import com.chamil.bugtrackerapi.model.entity.Comment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bugs/{bugId}")
public class CommentController {

    @GetMapping("/comments/{commentId}")
    public ResponseEntity<BugTrackerResponse<Comment>> getComment(@PathVariable Long bugId, Long commentId) {
        return new ResponseEntity<>(new BugTrackerResponse<>(true, new Comment()), HttpStatus.OK);
    }

    @PostMapping("/comments")
    public ResponseEntity<BugTrackerResponse<Comment>> createComment(@PathVariable Long bugId, @RequestBody CommentDTO commentDTO) {
        return new ResponseEntity<>(new BugTrackerResponse<>(true, new Comment()), HttpStatus.OK);
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<BugTrackerResponse<Comment>> updateComment(@PathVariable Long bugId, @PathVariable Long commentId) {
        return new ResponseEntity<>(new BugTrackerResponse<>(true, new Comment()), HttpStatus.OK);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<BugTrackerResponse<Comment>> deleteComment(@PathVariable Long bugId, @PathVariable Long commentId) {
        return new ResponseEntity<>(new BugTrackerResponse<>(true, new Comment()), HttpStatus.OK);
    }
}
