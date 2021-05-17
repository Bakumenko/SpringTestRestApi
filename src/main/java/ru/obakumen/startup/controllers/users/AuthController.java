package ru.obakumen.startup.controllers.users;

import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.obakumen.startup.dto.LoginDto;
import ru.obakumen.startup.models.Role;
import ru.obakumen.startup.models.User;
import ru.obakumen.startup.security.jwt.JwtProvider;
import ru.obakumen.startup.services.RolesService;
import ru.obakumen.startup.services.UsersService;

@RestController
public class AuthController {
    @Autowired
    private UsersService usersService;

    @Autowired
    private RolesService rolesService;

    @Autowired
    private JwtProvider jwtProvider;

    @GetMapping("/user/current")
    public ResponseEntity<?> getCurrentUser() {
        String username = jwtProvider.getCurrentUsername();
        User finded_user =  usersService.findUserByUsername(username);
        if (finded_user != null)
            return new ResponseEntity<>(finded_user, HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping ("/user/current")
    public Long deleteCurrentUser() {
        String username = jwtProvider.getCurrentUsername();
        return usersService.deleteUser(username);
    }

    @GetMapping("/user/current/projects")
    public ResponseEntity<?> getCurrentUserProjects() {
        String username = jwtProvider.getCurrentUsername();
        User finded_user =  usersService.findUserByUsername(username);
        if (finded_user != null)
            return new ResponseEntity<>(finded_user.getProjects(), HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/auth")
    public JSONObject auth(@RequestBody LoginDto request) {
        User user = usersService.findUserByUsernameAndPassword(request.getUsername(), request.getPassword());
        String token = jwtProvider.generateToken(user.getUsername());
        JSONObject json= new JSONObject();
        json.put("token", token);
        json.put("role", user.getRole().getName());
        return json;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (usersService.findUserByUsername(user.getUsername()) != null)
            return new ResponseEntity<>(null, HttpStatus.METHOD_NOT_ALLOWED);
        else {
            Role role = rolesService.findByRoleName("ROLE_USER");
            user.setRole(role);
            return new ResponseEntity<>(usersService.createNewUser(user), HttpStatus.OK);
            //return ResponseEntity.ok(userService.createNew(user));
        }
    }
}
