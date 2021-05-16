package ru.obakumen.startup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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
    public Project getProject(@PathVariable Long id) {
        return projectsService.findProjectById(id);
    }

    @PostMapping("")
    public Project createProject(@RequestBody Project project) {
        return projectsService.createNewProject(project);
    }

    @DeleteMapping("/{id}")
    public Long deleteProject(@PathVariable Long id) {
        return projectsService.deleteById(id);
    }
}
