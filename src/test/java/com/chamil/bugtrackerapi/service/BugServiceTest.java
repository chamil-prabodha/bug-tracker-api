package com.chamil.bugtrackerapi.service;

import com.chamil.bugtrackerapi.dao.BugRepository;
import com.chamil.bugtrackerapi.exception.APIException;
import com.chamil.bugtrackerapi.exception.ErrorCode;
import com.chamil.bugtrackerapi.model.entity.Bug;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class BugServiceTest {
    private final IssueService<Bug> bugService;

    @MockBean
    private BugRepository bugRepository;

    @Autowired
    public BugServiceTest(IssueService<Bug> bugService) {
        this.bugService = bugService;
    }

    @Sql("sql/bug-service-test.sql")
    @Transactional
    @Test
    void getBugErrorTest() throws APIException {
        Mockito.when(bugRepository.findBugByProjectIdAndId(Mockito.anyLong(), Mockito.anyLong())).thenThrow(new RuntimeException("Unknown Error"));
        APIException exception = assertThrows(APIException.class, () -> bugService.get(2L, 1L));
        assertEquals("An unknown error occurred", exception.getMessage());
        assertEquals(ErrorCode.UNKNOWN_EXCEPTION, exception.getErrorCode());
        assertEquals("Unknown Error", exception.getAdditionalInfo());
    }
}
