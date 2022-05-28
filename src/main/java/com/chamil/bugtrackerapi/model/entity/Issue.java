package com.chamil.bugtrackerapi.model.entity;

import com.chamil.bugtrackerapi.model.entity.enums.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString(callSuper = true)
@MappedSuperclass
public abstract class Issue extends BaseEntity {
    private String name;
    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String reportedBy;
}
