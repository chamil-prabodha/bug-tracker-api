package com.chamil.bugtrackerapi.service;

import com.chamil.bugtrackerapi.exception.APIException;
import com.chamil.bugtrackerapi.model.entity.Bug;
import com.chamil.bugtrackerapi.model.entity.Issue;

import java.util.List;

public interface IssueService<T extends Issue> {
    T get(Long projectId, Long issueId) throws APIException;
    List<T> get(Long projectId);
    T create(Long projectId, T issue);
    T update(Long projectId, T issue);
    T delete(Long projectId, T issue);
}
