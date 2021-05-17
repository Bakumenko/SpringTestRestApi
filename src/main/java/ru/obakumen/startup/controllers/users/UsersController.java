package ru.obakumen.startup.controllers.users;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.obakumen.startup.models.Project;
import ru.obakumen.startup.models.User;
import ru.obakumen.startup.services.ProjectsService;
import ru.obakumen.startup.services.UsersService;
import javax.servlet.ServletRequest;
import java.util.List;

@RestController
@RequestMapping("users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @Autowired
    private ProjectsService projectsService;

    @GetMapping("")
    public List<User> getAllByRoleUser() {
        return usersService.findRoleUserAll();
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getUserRoleUserAndUsername(@PathVariable String username) {
        User findedUser =  usersService.findUserByUsernameAndRoleUser(username);
        if (findedUser != null)
            return new ResponseEntity<>(findedUser, HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

    }

    @GetMapping("/{username}/projects")
    public ResponseEntity<?> getProjectsByRoleUserAndUsername(@PathVariable String username) {
        User findedUser =  usersService.findUserByUsernameAndRoleUser(username);
        if (findedUser == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else {
            List<Project> projects = findedUser.getProjects();
            return new ResponseEntity<>(projects, HttpStatus.OK);
        }
    }

    @PutMapping("/{username}")
    public ResponseEntity<?> updateUserByUsername(@PathVariable String username, @RequestBody User newUser) {
        User findedUser = usersService.findUserByUsernameAndRoleUser(username);
        if (findedUser == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else {
            BeanUtils.copyProperties(newUser, findedUser,"id");
            return new ResponseEntity<>(usersService.createNewUser(findedUser), HttpStatus.OK);
        }
    }
}
