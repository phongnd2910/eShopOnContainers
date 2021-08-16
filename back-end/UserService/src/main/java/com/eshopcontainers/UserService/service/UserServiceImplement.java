package com.eshopcontainers.UserService.service;

import com.eshopcontainers.UserService.constant.Constant;
import com.eshopcontainers.UserService.dto.UserDTO;
import com.eshopcontainers.UserService.entity.User;
import com.eshopcontainers.UserService.mapper.UserMapper;
import com.eshopcontainers.UserService.model.HttpResponse;
import com.eshopcontainers.UserService.model.JwtRequest;
import com.eshopcontainers.UserService.model.JwtResponse;
import com.eshopcontainers.UserService.repository.UserRepository;
import com.eshopcontainers.UserService.utility.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplement implements UserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;


    @Override
    public ResponseEntity<HttpResponse> save(UserDTO userDTO) {
        try {
            User user = userMapper.toUser(userDTO);
            userRepository.save(user);
            return ResponseEntity.ok(HttpResponse.builder().code(Constant.OK).message("Save user successfully!").build());
        } catch (Exception e) {
            return ResponseEntity.ok(HttpResponse.builder().code(Constant.NOT_OK).message(e.getMessage()).build());
        }
    }

    @Override
    public ResponseEntity<JwtResponse> authenticate(JwtRequest request) throws Exception {
        String username = request.getUsername();
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, request.getPassword()));
        } catch (BadCredentialsException badCredentialsException) {
            throw new Exception("Invalidate credentials!", badCredentialsException);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return ResponseEntity.ok(JwtResponse.builder()
                .token(jwtUtility.generateToken(userDetails))
                .type(JWTUtility.TOKEN_TYPE)
                .username(userDetails.getUsername())
                .authorities(userDetails.getAuthorities().toArray())
                .build());
    }

    @Override
    public User getUserByName(String username) throws Exception {
        return userRepository.findByUsername(username).orElseThrow(() -> new Exception("User: " + username + " not found!"));
    }

}
