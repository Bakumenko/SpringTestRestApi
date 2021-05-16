package ru.obakumen.startup.controllers.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.obakumen.startup.models.User;
import ru.obakumen.startup.services.RolesService;
import ru.obakumen.startup.services.UsersService;

import java.util.List;

@RestController
@RequestMapping("admins")
public class AdminsController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private RolesService rolesService;

    @GetMapping("/users")
    public List<User> getAll() {
        return usersService.findAll();
    }


}
