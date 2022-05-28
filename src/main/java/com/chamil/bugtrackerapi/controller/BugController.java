package com.chamil.bugtrackerapi.controller;

import com.chamil.bugtrackerapi.exception.APIException;
import com.chamil.bugtrackerapi.model.dto.request.BugDTO;
import com.chamil.bugtrackerapi.model.dto.response.BugTrackerResponse;
import com.chamil.bugtrackerapi.model.entity.Bug;
import com.chamil.bugtrackerapi.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects/{projectId}")
public class BugController {
    private final IssueService<Bug> bugService;

    @Autowired
    public BugController(IssueService<Bug> bugService) {
        this.bugService = bugService;
    }

    @GetMapping("/bugs/{bugId}")
    public ResponseEntity<BugTrackerResponse<Bug>> getBug(@PathVariable Long projectId, @PathVariable Long bugId) throws APIException {
        Bug bug = bugService.get(projectId, bugId);
        BugTrackerResponse<Bug> apiResponse = new BugTrackerResponse<>(true, bug);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/bugs")
    public ResponseEntity<BugTrackerResponse<List<Bug>>> getBugs(@PathVariable Long projectId) throws APIException {
        List<Bug> bugs = bugService.get(projectId);
        BugTrackerResponse<List<Bug>> apiResponse = new BugTrackerResponse<>(true, bugs);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/bugs")
    public ResponseEntity<BugTrackerResponse<Bug>> createBug(@PathVariable Long projectId, @RequestBody BugDTO bugDTO) throws APIException {
        Bug bug = bugService.create(projectId, bugDTO.merge(new Bug()));
        BugTrackerResponse<Bug> apiResponse = new BugTrackerResponse<>(true, bug);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @PutMapping("/bugs/{bugId}")
    public ResponseEntity<BugTrackerResponse<Bug>> updateBug(@PathVariable Long projectId, @PathVariable Long bugId, @RequestBody BugDTO bugDTO) throws APIException {
        Bug bug = bugService.update(projectId, bugId, bugDTO.merge(new Bug()));
        BugTrackerResponse<Bug> apiResponse = new BugTrackerResponse<>(true, bug);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/bugs/{bugId}")
    public ResponseEntity<BugTrackerResponse<Bug>> deleteBug(@PathVariable Long projectId, @PathVariable Long bugId) throws APIException {
        Bug bug = bugService.delete(projectId, bugId);
        BugTrackerResponse<Bug> apiResponse = new BugTrackerResponse<>(true, bug);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
