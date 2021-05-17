package ru.obakumen.startup.services;

import ru.obakumen.startup.models.Project;
import ru.obakumen.startup.models.User;

import java.util.List;

public interface ProjectsService {
    List<Project> findAll();

    Project findProjectById(Long id);

    Long deleteById(Long id);

    Project createNewProject(Project newProject, String currentUserUsername);
}
