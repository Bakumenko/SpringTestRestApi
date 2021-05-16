package ru.obakumen.startup.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.obakumen.startup.models.User;
import ru.obakumen.startup.services.UsersService;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UsersService usersService;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = usersService.findUserByUsername(username);
        return CustomUserDetails.fromUserEntityToCustomUserDetails(user);
    }
}
