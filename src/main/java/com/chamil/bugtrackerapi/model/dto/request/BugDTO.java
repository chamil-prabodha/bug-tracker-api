package com.chamil.bugtrackerapi.model.dto.request;

import com.chamil.bugtrackerapi.model.entity.Bug;
import com.chamil.bugtrackerapi.model.entity.enums.Severity;
import com.chamil.bugtrackerapi.model.entity.enums.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BugDTO {
    private String name;
    private String description;
    private Status status;
    private Severity severity;
    private String reportedBy;

    public Bug merge(Bug bug) {
        if (bug != null) {
            bug.setName(name);
            bug.setDescription(description);
            bug.setStatus(status);
            bug.setSeverity(severity);
            bug.setReportedBy(reportedBy);
        }
        return bug;
    }
}
