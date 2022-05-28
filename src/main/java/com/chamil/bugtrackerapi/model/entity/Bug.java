package com.chamil.bugtrackerapi.model.entity;

import com.chamil.bugtrackerapi.model.entity.enums.Severity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@Entity
@DynamicUpdate
@ToString(callSuper = true)
public class Bug extends Issue {
    @Enumerated(EnumType.STRING)
    private Severity severity;

    public Bug merge(Bug bug) {
        if (bug != null) {
            if (bug.getSeverity() != null) {
                severity = bug.getSeverity();
            }
            if (bug.getName() != null) {
                setName(bug.getName());
            }
            if (bug.getDescription() != null) {
                setDescription(bug.getDescription());
            }
            if (bug.getReportedBy() != null) {
                setReportedBy(bug.getReportedBy());
            }
            if (bug.getStatus() != null) {
                setStatus(bug.getStatus());
            }
        }
        return this;
    }
}
