package ru.obakumen.startup.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.obakumen.startup.models.Role;
import ru.obakumen.startup.repositories.RolesRepository;
import ru.obakumen.startup.services.RolesService;

import java.util.List;

@Service
public class RolesServiceImpl implements RolesService {

    @Autowired
    private RolesRepository rolesRepository;

    @Override
    public List<Role> findAll() {
        return rolesRepository.findAll();
    }

    @Override
    public Role createNew(Role new_role) {
        Role role = rolesRepository.findRoleByName(new_role.getName());
        if (role == null)
            return rolesRepository.save(new_role);
        else
            return null;
    }

    @Override
    public Role findByRoleName(String roleName) {
        return rolesRepository.findRoleByName(roleName);
    }

    @Override
    public Long deleteRole(String roleName) {
        return rolesRepository.deleteByName(roleName);
    }
}
