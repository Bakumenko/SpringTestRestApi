package ru.obakumen.startup.controllers.users;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.obakumen.startup.dto.UserDto;
import ru.obakumen.startup.models.Project;
import ru.obakumen.startup.models.Role;
import ru.obakumen.startup.models.User;
import ru.obakumen.startup.services.RolesService;
import ru.obakumen.startup.services.UsersService;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminsController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private RolesService rolesService;

    @GetMapping("/users")
    public List<User> getUsersByRole(@RequestParam (required = false) String role) {
        if (role == null)
            return usersService.findAll();
        else
            return usersService.findUsersByRoleName(role);
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        User user = usersService.findUserByUsername(username);
        if (user != null)
            return new ResponseEntity<>(user, HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/users/{username}/projects")
    public ResponseEntity<?> getProjectsByRoleUserAndUsername(@PathVariable String username) {
        User findedUser =  usersService.findUserByUsernameAndRoleUser(username);
        if (findedUser == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else {
            List<Project> projects = findedUser.getProjects();
            return new ResponseEntity<>(projects, HttpStatus.OK);
        }
    }

    @PostMapping("/users")
    public User createNewUserWithRoleName(@RequestBody UserDto userDto) {
        Role role = rolesService.findByRoleName(userDto.getRoleName());
        if (role == null)
            return null;
        else {
            User user = new User(userDto.getUsername(), userDto.getPassword(), userDto.getFirstName(),
                                 userDto.getLastName(), userDto.getAge(), role);
            return usersService.createNew(user);
        }
    }

    @DeleteMapping("/users/{username}")
    public Long deleteUserByUsername(@PathVariable String username) {
        return usersService.deleteUser(username);
    }

    @GetMapping("/roles")
    public List<Role> getAllRoles() {
        return rolesService.findAll();
    }

    @PostMapping("/roles")
    public Role createNewRole(@RequestBody Role role) {
        return rolesService.createNew(role);
    }

    @DeleteMapping("/roles/{roleName}")
    public Long deleteRole(@PathVariable String roleName) {
        return rolesService.deleteRole(roleName);
    }

}
