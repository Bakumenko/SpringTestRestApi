package ru.obakumen.startup.services;

import org.springframework.http.ResponseEntity;
import ru.obakumen.startup.models.Role;
import ru.obakumen.startup.models.User;

import java.util.List;

public interface UsersService {

    List<User> findAll();

    List<User> findRoleUserAll();

    List<User> findUsersByRoleName(String roleName);

    User findUserByUsername(String username);

    User findUserByUsernameAndPassword(String username, String password);

    User findUserByUsernameAndRoleUser(String username);

    User createNew(User newUser);

    User createNewUser(User newUser);

    Long deleteUser(String username);
}
