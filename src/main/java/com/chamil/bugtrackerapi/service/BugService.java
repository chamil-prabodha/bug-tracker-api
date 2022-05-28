package com.chamil.bugtrackerapi.service;

import com.chamil.bugtrackerapi.dao.BugRepository;
import com.chamil.bugtrackerapi.exception.APIException;
import com.chamil.bugtrackerapi.exception.ErrorCode;
import com.chamil.bugtrackerapi.model.entity.Bug;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
public class BugService implements IssueService<Bug> {
    private final BugRepository bugRepository;

    @Autowired
    public BugService(BugRepository bugRepository) {
        this.bugRepository = bugRepository;
    }

    @Override
    public Bug get(Long projectId, Long bugId) throws APIException {
        try {
            return bugRepository.findBugByProjectIdAndId(projectId, bugId).orElseThrow();
        } catch (NoSuchElementException e) {
            log.warn("Requested bug not found for project id [{}] bug id [{}]", projectId, bugId);
            throw new APIException(ErrorCode.NOT_FOUND, String.format("Requested bug not found for project id [%s] bug id [%s]", projectId, bugId));
        } catch (Exception e) {
            log.error("An unknown exception occurred while retrieving the bug for project [{}] bug id [{}]", projectId, bugId, e);
            throw new APIException(ErrorCode.NOT_FOUND, e.getMessage());
        }
    }

    @Override
    public List<Bug> get(Long projectId) {
        return null;
    }

    @Override
    public Bug create(Long projectId, Bug issue) {
        return null;
    }

    @Override
    public Bug update(Long projectId, Bug issue) {
        return null;
    }

    @Override
    public Bug delete(Long projectId, Bug issue) {
        return null;
    }
}
