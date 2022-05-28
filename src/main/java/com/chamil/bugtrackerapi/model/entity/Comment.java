package com.chamil.bugtrackerapi.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Comment extends BaseEntity {
    private String author;
    private String content;
}
