package com.eshopcontainers.UserService.service;

import com.eshopcontainers.UserService.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


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
            //add authorities
            //ub.authorities(Arrays.asList(new SimpleGrantedAuthority(user.getUserGroup().getName())));
            return ub.build();
        } catch (Exception e) {
            throw new UsernameNotFoundException("User: " + username + " not found!", e);
        }
    }
}
