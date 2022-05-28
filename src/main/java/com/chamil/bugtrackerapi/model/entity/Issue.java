package com.chamil.bugtrackerapi.model.entity;

import com.chamil.bugtrackerapi.model.entity.enums.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@MappedSuperclass
public abstract class Issue extends BaseEntity {
    private String name;
    private String description;
    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
    @OneToMany(mappedBy = "issueId", fetch = FetchType.LAZY)
    private List<Comment> comments;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String reportedBy;
}
