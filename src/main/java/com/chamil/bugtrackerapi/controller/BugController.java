package com.chamil.bugtrackerapi.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects/{projectId}")
public class BugController {

    @GetMapping("/bugs/{bugId}")
    public void getBug(@PathVariable Long projectId, @PathVariable Long bugId) {

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
