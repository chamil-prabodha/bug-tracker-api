package com.chamil.bugtrackerapi.service;

import com.chamil.bugtrackerapi.dao.BugRepository;
import com.chamil.bugtrackerapi.dao.ProjectRepository;
import com.chamil.bugtrackerapi.exception.APIException;
import com.chamil.bugtrackerapi.exception.ErrorCode;
import com.chamil.bugtrackerapi.model.entity.Bug;
import com.chamil.bugtrackerapi.model.entity.Project;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class BugServiceTest {
    private final IssueService<Bug> bugService;

    @MockBean
    private BugRepository bugRepository;
    @MockBean
    private ProjectRepository projectRepository;

    @Autowired
    public BugServiceTest(IssueService<Bug> bugService) {
        this.bugService = bugService;
    }

    @Test
    void getBugErrorTest()  {
        Mockito.when(bugRepository.findBugByProjectIdAndId(Mockito.anyLong(), Mockito.anyLong())).thenThrow(new RuntimeException("Unknown Error"));
        APIException exception = assertThrows(APIException.class, () -> bugService.get(2L, 1L));
        assertEquals("An unknown error occurred", exception.getMessage());
        assertEquals(ErrorCode.UNKNOWN_EXCEPTION, exception.getErrorCode());
        assertEquals("Unknown Error", exception.getAdditionalInfo());
    }

    @Test
    void getAllBugsErrorTest() {
        Mockito.when(bugRepository.findAllByProjectId(Mockito.anyLong())).thenThrow(new RuntimeException("Unknown Error"));
        APIException exception = assertThrows(APIException.class, () -> bugService.get(2L));
        assertEquals("An unknown error occurred", exception.getMessage());
        assertEquals(ErrorCode.UNKNOWN_EXCEPTION, exception.getErrorCode());
        assertEquals("Unknown Error", exception.getAdditionalInfo());
    }

    @Test
    void createBugErrorTest() {
        Mockito.when(bugRepository.save(Mockito.any(Bug.class))).thenThrow(new RuntimeException("Unknown Error"));
        Mockito.when(projectRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(new Project()));
        APIException exception = assertThrows(APIException.class, () -> bugService.create(1L, new Bug()));
        assertEquals("An unknown error occurred", exception.getMessage());
        assertEquals(ErrorCode.UNKNOWN_EXCEPTION, exception.getErrorCode());
        assertEquals("Unknown Error", exception.getAdditionalInfo());
    }

    @Test
    void updateBugErrorTest() {
        Mockito.when(bugRepository.save(Mockito.any(Bug.class))).thenThrow(new RuntimeException("Unknown Error"));
        Mockito.when(bugRepository.findBugByProjectIdAndId(Mockito.anyLong(), Mockito.anyLong())).thenReturn(Optional.of(new Bug()));
        APIException exception = assertThrows(APIException.class, () -> bugService.update(1L, 1L, new Bug()));
        assertEquals("An unknown error occurred", exception.getMessage());
        assertEquals(ErrorCode.UNKNOWN_EXCEPTION, exception.getErrorCode());
        assertEquals("Unknown Error", exception.getAdditionalInfo());
    }

    @Test
    void deleteBugErrorTest() {
        Mockito.doThrow(new RuntimeException("Unknown Error")).when(bugRepository).delete(Mockito.any(Bug.class));
        Mockito.when(bugRepository.findBugByProjectIdAndId(Mockito.anyLong(), Mockito.anyLong())).thenReturn(Optional.of(new Bug()));
        APIException exception = assertThrows(APIException.class, () -> bugService.delete(1L, 1L));
        assertEquals("An unknown error occurred", exception.getMessage());
        assertEquals(ErrorCode.UNKNOWN_EXCEPTION, exception.getErrorCode());
        assertEquals("Unknown Error", exception.getAdditionalInfo());
    }
}
