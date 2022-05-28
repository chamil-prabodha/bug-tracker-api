package com.chamil.bugtrackerapi.service;

import com.chamil.bugtrackerapi.exception.APIException;
import com.chamil.bugtrackerapi.exception.ErrorCode;
import com.chamil.bugtrackerapi.model.entity.Bug;
import com.chamil.bugtrackerapi.model.entity.enums.Severity;
import com.chamil.bugtrackerapi.model.entity.enums.Status;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class BugServiceTest {

    private final IssueService<Bug> bugService;

    @Autowired
    public BugServiceTest(IssueService<Bug> bugService) {
        this.bugService = bugService;
    }

    @Sql("sql/bug-service-test.sql")
    @Transactional
    @Test
    void getBugTest() throws APIException {
        Bug actual = bugService.get(1L, 1L);

        assertNotNull(actual);
        assertEquals(1, actual.getId());
        assertEquals("Bug 1", actual.getName());
        assertEquals(Status.OPEN, actual.getStatus());
        assertEquals(Severity.LOW, actual.getSeverity());
        assertEquals("USER", actual.getReportedBy());
        assertNotNull(actual.getProject());
        assertEquals("project-1", actual.getProject().getProjectKey());
        assertEquals("Project 1", actual.getProject().getName());
    }

    @Sql("sql/bug-service-test.sql")
    @Transactional
    @Test
    void getBugNotFoundTest() throws APIException {
        APIException exception = assertThrows(APIException.class, () -> bugService.get(2L, 1L));
        assertEquals("Requested resource not found", exception.getMessage());
        assertEquals(ErrorCode.NOT_FOUND, exception.getErrorCode());
        assertEquals("Requested bug not found for project id [2] bug id [1]", exception.getAdditionalInfo());
    }
}