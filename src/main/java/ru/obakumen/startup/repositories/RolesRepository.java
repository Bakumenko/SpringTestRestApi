package ru.obakumen.startup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.obakumen.startup.models.Role;

public interface RolesRepository extends JpaRepository<Role, Long> {
    Role findRoleByName(String name);
}
