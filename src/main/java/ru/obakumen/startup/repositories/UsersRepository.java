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

    User findOneByUsername(String username);
    Long deleteByUsername(String username);
    List<User> findUserByRole(Role role);
}
