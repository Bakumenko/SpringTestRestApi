package ru.obakumen.startup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.obakumen.startup.models.Project;
import ru.obakumen.startup.models.User;
import ru.obakumen.startup.security.jwt.JwtProvider;
import ru.obakumen.startup.services.ProjectsService;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectsController {
    @Autowired
    private ProjectsService projectsService;

    @Autowired
    private JwtProvider jwtProvider;

    @GetMapping("")
    public List<Project> getAll() {
        return projectsService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProject(@PathVariable Long id) {
        Project project = projectsService.findProjectById(id);
        if (project != null)
            return new ResponseEntity<>(project, HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping("")
    public Project createProject(@RequestBody Project project) {
        return projectsService.createNewProject(project, jwtProvider.getCurrentUsername());
    }

    @DeleteMapping("/{id}")
    public Long deleteProject(@PathVariable Long id) {
        return projectsService.deleteById(id);
    }
}
