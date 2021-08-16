package com.eshopcontainers.UserService.service;

import com.eshopcontainers.UserService.dto.UserDTO;
import com.eshopcontainers.UserService.entity.User;
import com.eshopcontainers.UserService.model.HttpResponse;
import com.eshopcontainers.UserService.model.JwtRequest;
import com.eshopcontainers.UserService.model.JwtResponse;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<HttpResponse> save(UserDTO userDTO);
    ResponseEntity<JwtResponse> authenticate(JwtRequest request) throws Exception;
    User getUserByName(String username) throws Exception;

}
