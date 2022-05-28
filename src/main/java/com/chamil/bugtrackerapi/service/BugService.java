package com.chamil.bugtrackerapi.service;

import com.chamil.bugtrackerapi.dao.BugRepository;
import com.chamil.bugtrackerapi.dao.ProjectRepository;
import com.chamil.bugtrackerapi.exception.APIException;
import com.chamil.bugtrackerapi.exception.ErrorCode;
import com.chamil.bugtrackerapi.model.entity.Bug;
import com.chamil.bugtrackerapi.model.entity.Project;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
public class BugService implements IssueService<Bug> {
    private final BugRepository bugRepository;
    private final ProjectRepository projectRepository;

    @Autowired
    public BugService(BugRepository bugRepository, ProjectRepository projectRepository) {
        this.bugRepository = bugRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public Bug get(Long projectId, Long bugId) throws APIException {
        try {
            return bugRepository.findBugByProjectIdAndId(projectId, bugId).orElseThrow();
        } catch (NoSuchElementException e) {
            log.warn("Requested bug not found for project id [{}] bug id [{}]", projectId, bugId, e);
            throw new APIException(ErrorCode.NOT_FOUND, String.format("Requested bug not found for project id [%s] bug id [%s]", projectId, bugId));
        } catch (Exception e) {
            log.error("An unknown exception occurred while retrieving the bug for project [{}] bug id [{}]", projectId, bugId, e);
            throw new APIException(ErrorCode.UNKNOWN_EXCEPTION, e.getMessage());
        }
    }

    @Override
    public List<Bug> get(Long projectId) throws APIException {
        try {
            return bugRepository.findAllByProjectId(projectId);
        } catch (Exception e) {
            log.error("An unknown exception occurred while retrieving all the bugs for project [{}] ", projectId, e);
            throw new APIException(ErrorCode.UNKNOWN_EXCEPTION, e.getMessage());
        }
    }

    @Override
    public Bug create(Long projectId, Bug bug) throws APIException {
        try {
            Project project = projectRepository.findById(projectId).orElseThrow();
            bug.setProject(project);
            return bugRepository.save(bug);
        } catch (NoSuchElementException e) {
            log.warn("Requested project was not found for project id [{}]", projectId, e);
            throw new APIException(ErrorCode.NOT_FOUND, String.format("Requested project was not found for project id [%s]", projectId));
        } catch (Exception e) {
            log.error("An unknown exception occurred while creating bug [{}] for project id [{}] ", bug, projectId, e);
            throw new APIException(ErrorCode.UNKNOWN_EXCEPTION, e.getMessage());
        }
    }

    @Override
    public Bug update(Long projectId, Long bugId, Bug bug) throws APIException {
        try {
            Bug existingBug = bugRepository.findBugByProjectIdAndId(projectId, bugId).orElseThrow();
            existingBug = existingBug.merge(bug);
            return bugRepository.save(existingBug);
        } catch (NoSuchElementException e) {
            log.warn("Requested bug was not found for project id [{}] bug id [{}]", projectId, bugId, e);
            throw new APIException(ErrorCode.NOT_FOUND, String.format("Requested bug was not found for project id [%s] bug id [%s]", projectId, bugId));
        } catch (Exception e) {
            log.error("An unknown exception occurred while updating bug id [{}] for project id [{}] ", bugId, projectId, e);
            throw new APIException(ErrorCode.UNKNOWN_EXCEPTION, e.getMessage());
        }
    }

    @Override
    public Bug delete(Long projectId, Long bugId) throws APIException {
        try {
            Bug existingBug = bugRepository.findBugByProjectIdAndId(projectId, bugId).orElseThrow();
            bugRepository.delete(existingBug);
            return existingBug;
        } catch (NoSuchElementException e) {
            log.warn("Requested bug was not found for project id [{}] bug id [{}]", projectId, bugId, e);
            throw new APIException(ErrorCode.NOT_FOUND, String.format("Requested bug was not found for project id [%s] bug id [%s]", projectId, bugId));
        } catch (Exception e) {
            log.error("An unknown exception occurred while updating bug id [{}] for project id [{}] ", bugId, projectId, e);
            throw new APIException(ErrorCode.UNKNOWN_EXCEPTION, e.getMessage());
        }
    }
}
