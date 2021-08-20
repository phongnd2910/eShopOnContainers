package com.eshopcontainers.UserService.service;

import com.eshopcontainers.UserService.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;


@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Logic to get user from the database
        try {
            User user = userService.getUserByName(username);
            UserBuilder ub = org.springframework.security.core.userdetails.User.withUsername(username);
            ub.password(user.getPassword());
            ub.authorities(Arrays.asList(new SimpleGrantedAuthority("default")));
            return ub.build();
        } catch (Exception e) {
            throw new UsernameNotFoundException("User: " + username + " not found!", e);
        }
    }
}
