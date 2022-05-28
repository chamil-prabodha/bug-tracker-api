package com.chamil.bugtrackerapi.controller;

import com.chamil.bugtrackerapi.exception.APIException;
import com.chamil.bugtrackerapi.model.dto.BugTrackerResponse;
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
    public void createBug(@PathVariable Long projectId) {

    }

    @PutMapping("/bugs/{bugId}")
    public void updateBug(@PathVariable Long projectId, @PathVariable Long bugId) {

    }
}
