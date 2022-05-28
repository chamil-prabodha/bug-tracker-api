package com.chamil.bugtrackerapi.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
@DynamicUpdate
public class Comment extends BaseEntity {
    private Long issueId;
    private String author;
    private String content;
}
