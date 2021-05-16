package ru.obakumen.startup.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.obakumen.startup.models.Role;
import ru.obakumen.startup.models.User;
import ru.obakumen.startup.repositories.RolesRepository;
import ru.obakumen.startup.repositories.UsersRepository;
import ru.obakumen.startup.services.UsersService;
import org.springframework.security.crypto.password.PasswordEncoder;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RolesRepository rolesRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> findAll() {
        return usersRepository.findAll();
    }

    @Override
    public List<User> findRoleUserAll() {
        Role role = rolesRepository.findRoleByName("ROLE_USER");
        List<User> users = usersRepository.findUsersByRole(role);
        return users;
    }

    @Override
    public List<User> findUsersByRoleName(String roleName) {
        Role role = rolesRepository.findRoleByName(roleName);
        if (role != null)
            return usersRepository.findUsersByRole(role);
        else
            return null;
    }

    @Override
    public User findUserByUsername(String username) {
        return usersRepository.findUserByUsername(username);
    }

    @Override
    public User findUserByUsernameAndRoleUser(String username) {
        Role role = rolesRepository.findRoleByName("ROLE_USER");
        User user = usersRepository.findUserByUsernameAndRole(username, role);
        return user;
    }

    @Override
    public User createNew(User newUser) {
        return usersRepository.save(newUser);
    }

    @Override
    public User createNewUser(User newUser) {
        Role role = rolesRepository.findRoleByName("ROLE_USER");
        newUser.setRole(role);
        //new_user.setPassword(passwordEncoder.encode(new_user.getPassword()));
        return usersRepository.save(newUser);
    }

    @Override
    public Long deleteUser(String username) {
        return usersRepository.deleteUserByUsername(username);
    }


    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        User user = findUserByUsername(username);
        if (user.getPassword().equals(password))
            return user;
//        if (userEntity != null) {
//            if (passwordEncoder.matches(password, userEntity.getPassword())) {
//                return userEntity;
//            }
//        }
        return null;
    }
}
