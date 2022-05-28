package com.chamil.bugtrackerapi.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

@Getter
@Setter
@ToString
@Entity
public class Project extends BaseEntity {
    private String projectKey;
    private String name;
}
