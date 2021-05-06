package ru.obakumen.startup.services;

import org.springframework.http.ResponseEntity;
import ru.obakumen.startup.models.Role;
import ru.obakumen.startup.models.User;

import java.util.List;

public interface UsersService {
    List<User> findAll();

    List<User> findByRole(Role role);

    User findOneByUsername(String username);

    User createNew(User user);

    Long delete(String username);
}
