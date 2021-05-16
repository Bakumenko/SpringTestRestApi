package ru.obakumen.startup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import ru.obakumen.startup.models.Role;
import ru.obakumen.startup.models.User;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {

    User findUserByUsername(String username);
    User findUserByUsernameAndRole(String username, Role role);
    List<User> findUsersByRole(Role role);
    Long deleteUserByUsername(String username);
}
