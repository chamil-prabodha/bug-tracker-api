package com.chamil.bugtrackerapi.controller;

import com.chamil.bugtrackerapi.exception.APIException;
import com.chamil.bugtrackerapi.model.dto.BugTrackerResponse;
import com.chamil.bugtrackerapi.model.entity.Bug;
import com.chamil.bugtrackerapi.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        BugTrackerResponse<Bug> apiResponse = new com.chamil.bugtrackerapi.model.dto.BugTrackerResponse<>(true, bug);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/bugs")
    public void getBugs(@PathVariable Long projectId) {

    }

    @PostMapping("/bugs")
    public void createBug(@PathVariable Long projectId) {

    }

    @PutMapping("/bugs/{bugId}")
    public void updateBug(@PathVariable Long projectId, @PathVariable Long bugId) {

    }
}
