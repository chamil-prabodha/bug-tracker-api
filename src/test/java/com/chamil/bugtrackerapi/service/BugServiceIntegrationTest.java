package com.chamil.bugtrackerapi.service;

import com.chamil.bugtrackerapi.TestUtil;
import com.chamil.bugtrackerapi.exception.APIException;
import com.chamil.bugtrackerapi.exception.ErrorCode;
import com.chamil.bugtrackerapi.model.entity.Bug;
import com.chamil.bugtrackerapi.model.entity.enums.Severity;
import com.chamil.bugtrackerapi.model.entity.enums.Status;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BugServiceIntegrationTest {
    private static final ObjectMapper objectMapper =  new ObjectMapper();
    private final IssueService<Bug> bugService;

    @Autowired
    public BugServiceIntegrationTest(IssueService<Bug> bugService) {
        this.bugService = bugService;
    }

    @Sql("sql/bug-service-test.sql")
    @Transactional
    @Test
    void getBugSuccessTest() throws APIException {
        Bug actual = bugService.get(1L, 1L);

        assertNotNull(actual);
        assertEquals(1, actual.getId());
        assertEquals("Bug 1", actual.getName());
        assertEquals(Status.OPEN, actual.getStatus());
        assertEquals(Severity.LOW, actual.getSeverity());
        assertEquals("USER 1", actual.getReportedBy());
        assertNotNull(actual.getProject());
        assertEquals("project-1", actual.getProject().getProjectKey());
        assertEquals("Project 1", actual.getProject().getName());
    }

    @Sql("sql/bug-service-test.sql")
    @Transactional
    @Test
    void getBugNotFoundTest() {
        APIException exception = assertThrows(APIException.class, () -> bugService.get(2L, 1L));
        assertEquals("Requested resource not found", exception.getMessage());
        assertEquals(ErrorCode.NOT_FOUND, exception.getErrorCode());
        assertEquals("Requested bug not found for project id [2] bug id [1]", exception.getAdditionalInfo());
    }

    @Sql("sql/bug-service-test.sql")
    @Transactional
    @Test
    void getAllBugsSuccessTest() throws JsonProcessingException, JSONException, APIException {
        String expected = TestUtil.loadResourceAsString("json/bug-service-test.json", getClass());

        List<Bug> actual = bugService.get(1L);
        String actualJson = objectMapper.writeValueAsString(actual);

        JSONAssert.assertEquals(expected, actualJson, JSONCompareMode.STRICT);
    }

    @Sql("sql/bug-service-test.sql")
    @Transactional
    @Test
    void getAllBugsEmptyTest() throws APIException {
        List<Bug> actual = bugService.get(2L);
        assertTrue(actual.isEmpty());
    }
}
