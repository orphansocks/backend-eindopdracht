package nl.novi.backendeindopdracht.services;

import nl.novi.backendeindopdracht.dtos.user.UserDto;
import nl.novi.backendeindopdracht.models.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    // waar komt de term GrantedAuthority / SimpleGrantedAuthority vandaan?

    @Override
    public UserDetails loadUserByUsername(String username) {

        UserDto userDto = userService.getUser(username);
        String password = userDto.getPassword();

        Set<Role> roles = userDto.getRoles();

        List<GrantedAuthority> assignedRoles = new ArrayList<>();

        for (Role role: roles) {
            assignedRoles.add(new SimpleGrantedAuthority(role.getRole()));
        }

        return new org.springframework.security.core.userdetails.User(username, password, assignedRoles);
    }
}
