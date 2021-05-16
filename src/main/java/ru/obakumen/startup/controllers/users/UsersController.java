package ru.obakumen.startup.controllers.users;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.obakumen.startup.models.User;
import ru.obakumen.startup.services.UsersService;
import javax.servlet.ServletRequest;
import java.util.List;

@RestController
@RequestMapping("users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @GetMapping("")
    public List<User> getAllRoleUser() {
        return usersService.findRoleUserAll();
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getRoleUserByUsername(@PathVariable String username) {
        User findedUser =  usersService.findUserByUsernameAndRoleUser(username);
        if (findedUser != null)
            return new ResponseEntity<>(findedUser, HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

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
