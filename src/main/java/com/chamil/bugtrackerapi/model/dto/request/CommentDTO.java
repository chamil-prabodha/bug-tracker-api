package com.chamil.bugtrackerapi.model.dto.request;

import com.chamil.bugtrackerapi.model.entity.Comment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDTO {
    private String content;
    private String author;

    public Comment merge(Comment comment) {
        if (comment != null) {
            comment.setContent(content);
            comment.setAuthor(author);
        }
        return comment;
    }
 }
