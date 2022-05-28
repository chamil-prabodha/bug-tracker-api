package com.chamil.bugtrackerapi.model.entity;

import com.chamil.bugtrackerapi.model.entity.enums.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@MappedSuperclass
public abstract class Issue extends BaseEntity {
    private String name;
    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private Project project;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String reportedBy;
}
