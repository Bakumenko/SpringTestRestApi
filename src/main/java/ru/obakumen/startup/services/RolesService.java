package ru.obakumen.startup.services;

import ru.obakumen.startup.models.Role;

import java.util.List;

public interface RolesService {
    List<Role> findAll();

    Role createNew(Role newRole);

    Role findByRoleName(String roleName);

    Long deleteRole(String roleName);
}
