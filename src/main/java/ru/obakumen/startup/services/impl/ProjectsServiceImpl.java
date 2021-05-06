package ru.obakumen.startup.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.obakumen.startup.models.Project;
import ru.obakumen.startup.models.User;
import ru.obakumen.startup.repositories.ProjectsRepository;
import ru.obakumen.startup.repositories.UsersRepository;
import ru.obakumen.startup.services.ProjectsService;

import java.util.List;

@Service
public class ProjectsServiceImpl implements ProjectsService {
    @Autowired
    private ProjectsRepository projectsRepository;

    @Override
    public List<Project> findAll() {
        return projectsRepository.findAll();
    }
}
