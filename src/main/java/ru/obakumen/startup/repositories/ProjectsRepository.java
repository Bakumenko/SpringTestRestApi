package ru.obakumen.startup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.obakumen.startup.models.Project;

@Repository
public interface ProjectsRepository extends JpaRepository<Project, Long> {
    Project findProjectById(Long id);

    Long deleteProjectById(Long id);
}
