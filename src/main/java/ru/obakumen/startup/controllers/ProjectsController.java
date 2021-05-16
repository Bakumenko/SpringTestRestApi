package ru.obakumen.startup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.obakumen.startup.models.Project;
import ru.obakumen.startup.models.User;
import ru.obakumen.startup.services.ProjectsService;

import java.util.List;

@RestController
public class ProjectsController {
    @Autowired
    private ProjectsService projectsService;

    @GetMapping("/projects")
    public List<Project> getAll() {
        return projectsService.findAll();
    }
}
