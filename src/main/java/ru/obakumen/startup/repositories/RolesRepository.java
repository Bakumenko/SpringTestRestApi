package ru.obakumen.startup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.obakumen.startup.models.Role;

@Repository
public interface RolesRepository extends JpaRepository<Role, Long> {
    Role findRoleByName(String name);

    Long deleteRoleByName(String roleName);
}
