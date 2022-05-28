package com.chamil.bugtrackerapi.service;

import com.chamil.bugtrackerapi.exception.APIException;
import com.chamil.bugtrackerapi.model.entity.Issue;

import java.util.List;

public interface IssueService<T extends Issue> {
    T get(Long projectId, Long issueId) throws APIException;
    List<T> get(Long projectId) throws APIException;
    T create(Long projectId, T issue) throws APIException;
    T update(Long projectId, Long issueId, T issue) throws APIException;
    T delete(Long projectId, Long issueId) throws APIException;
}
