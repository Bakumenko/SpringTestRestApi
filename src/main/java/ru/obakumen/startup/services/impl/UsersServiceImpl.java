package ru.obakumen.startup.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.obakumen.startup.models.Role;
import ru.obakumen.startup.models.User;
import ru.obakumen.startup.repositories.UsersRepository;
import ru.obakumen.startup.services.UsersService;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<User> findAll() {
        return usersRepository.findAll();
    }

    @Override
    public List<User> findByRole(Role role) {
        return usersRepository.findUserByRole(role);
    }

    @Override
    public User findOneByUsername(String username) {
        return usersRepository.findOneByUsername(username);
    }

    @Override
    public User createNew(User user) {
        return usersRepository.save(user);
    }

    @Override
    public Long delete(String username) {
        return usersRepository.deleteByUsername(username);
    }
}
