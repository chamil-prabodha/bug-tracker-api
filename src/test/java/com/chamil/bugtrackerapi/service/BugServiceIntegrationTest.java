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
        Bug actual = bugService.get(1L, 10L);

        assertNotNull(actual);
        assertEquals(10, actual.getId());
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

    @Sql("sql/bug-service-test.sql")
    @Transactional
    @Test
    void createBugSuccessTest() throws APIException {
        Bug bug = new Bug();
        bug.setName("New Bug");
        bug.setDescription("Creating New Bug");
        bug.setSeverity(Severity.LOW);
        bug.setReportedBy("USER 3");
        bug.setStatus(Status.OPEN);

        Bug actual = bugService.create(1L, bug);

        assertNotNull(actual);
        assertEquals(1, actual.getId());
        assertEquals("Project 1", actual.getProject().getName());
    }

    @Sql("sql/bug-service-test.sql")
    @Transactional
    @Test
    void createBugProjectNotFoundTest() {
        APIException exception = assertThrows(APIException.class, () ->  bugService.create(2L, new Bug()));
        assertEquals("Requested resource not found", exception.getMessage());
        assertEquals(ErrorCode.NOT_FOUND, exception.getErrorCode());
        assertEquals("Requested project was not found for project id [2]", exception.getAdditionalInfo());
    }

    @Sql("sql/bug-service-test.sql")
    @Transactional
    @Test
    void updateBugSuccessTest() throws APIException {
        Bug bug = new Bug();
        bug.setName("Updated Bug");
        bug.setDescription("Updating Bug");
        bug.setStatus(Status.CLOSED);
        bug.setReportedBy("USER 3");

        Bug actual = bugService.update(1L, 10L, bug);

        assertNotNull(actual);
        assertEquals(10, actual.getId());
        assertEquals("Updated Bug", actual.getName());
        assertEquals("Updating Bug", actual.getDescription());
        assertEquals(Status.CLOSED, actual.getStatus());
        assertEquals("USER 3", actual.getReportedBy());
        assertEquals(Severity.LOW, actual.getSeverity());
    }

    @Sql("sql/bug-service-test.sql")
    @Transactional
    @Test
    void updateBugNotFoundTest() {
        Bug bug = new Bug();
        bug.setName("Updated Bug");
        bug.setDescription("Updating Bug");
        bug.setStatus(Status.CLOSED);
        bug.setReportedBy("USER 3");

        APIException exception = assertThrows(APIException.class, () ->  bugService.update(1L, 1L, bug));
        assertEquals("Requested resource not found", exception.getMessage());
        assertEquals(ErrorCode.NOT_FOUND, exception.getErrorCode());
        assertEquals("Requested bug was not found for project id [1] bug id [1]", exception.getAdditionalInfo());
    }

    @Sql("sql/bug-service-test.sql")
    @Transactional
    @Test
    void deleteBugSuccessTest() throws APIException {
        Bug actual = bugService.delete(1L, 10L);

        assertNotNull(actual);
        assertEquals(10, actual.getId());
        assertEquals("Bug 1", actual.getName());
        assertEquals("Description 1", actual.getDescription());
        assertEquals(Status.OPEN, actual.getStatus());
        assertEquals("USER 1", actual.getReportedBy());
        assertEquals(Severity.LOW, actual.getSeverity());

        APIException exception = assertThrows(APIException.class, () -> bugService.get(1L, 10L));
    }

    @Sql("sql/bug-service-test.sql")
    @Transactional
    @Test
    void deleteBugNotFoundTest() {
        APIException exception = assertThrows(APIException.class, () ->  bugService.delete(1L, 1L));
        assertEquals("Requested resource not found", exception.getMessage());
        assertEquals(ErrorCode.NOT_FOUND, exception.getErrorCode());
        assertEquals("Requested bug was not found for project id [1] bug id [1]", exception.getAdditionalInfo());
    }
}
