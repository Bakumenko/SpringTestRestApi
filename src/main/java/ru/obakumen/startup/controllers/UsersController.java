package ru.obakumen.startup.controllers;

import net.minidev.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.obakumen.startup.models.Role;
import ru.obakumen.startup.models.User;
import ru.obakumen.startup.services.UsersService;

import java.util.List;

@RestController
public class UsersController {
    @Autowired
    private UsersService usersService;

    @GetMapping("/users")
    public List<User> getAll() {
        return usersService.findAll();
    }

    @GetMapping("/admin_users")
    public List<User> getAdmins() {
        return usersService.findByRole(Role.Admin);
    }

    @GetMapping("/client_users")
    public List<User> getClents() {
        return usersService.findByRole(Role.Client);
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        User finded_user =  usersService.findOneByUsername(username);
        HttpStatus status;
        if (finded_user != null)
            status = HttpStatus.OK;
        else
            status = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(finded_user, status);
    }

    @PutMapping("/users/{username}")
    public ResponseEntity<?> updateUserByUsername(@PathVariable String username, @RequestBody User new_user) {
        User finded_user = usersService.findOneByUsername(username);
        if (finded_user == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else {
            BeanUtils.copyProperties(new_user, finded_user,"id");
            return new ResponseEntity<>(usersService.createNew(finded_user), HttpStatus.OK);
        }
    }

    @PostMapping("/admin_user")
    public ResponseEntity<?> createAdmin(@RequestBody User user) {
        if (usersService.findOneByUsername(user.getUsername()) != null)
            return new ResponseEntity<>(null, HttpStatus.METHOD_NOT_ALLOWED);
        else {
            User admin_user = user;
            admin_user.setRole(Role.Admin);
            return new ResponseEntity<>(usersService.createNew(user), HttpStatus.OK);
        }
    }

    @PostMapping("/client_user")
    public ResponseEntity<?> createClient(@RequestBody User user) {
        if (usersService.findOneByUsername(user.getUsername()) != null)
            return new ResponseEntity<>(null, HttpStatus.METHOD_NOT_ALLOWED);
        else {
            User admin_user = user;
            admin_user.setRole(Role.Client);
            return new ResponseEntity<>(usersService.createNew(user), HttpStatus.OK);
        }
    }

    @DeleteMapping("/users/{username}")
    public JSONObject delete(@PathVariable String username) {
        JSONObject json= new JSONObject();
        Long count = usersService.delete(username);
        json.put("count of deleting", count);
        return json;
    }
}
