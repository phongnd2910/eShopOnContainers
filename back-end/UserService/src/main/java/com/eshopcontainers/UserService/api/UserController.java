package com.eshopcontainers.UserService.api;

import com.eshopcontainers.UserService.dto.UserDTO;
import com.eshopcontainers.UserService.model.HttpResponse;
import com.eshopcontainers.UserService.model.JwtRequest;
import com.eshopcontainers.UserService.model.JwtResponse;
import com.eshopcontainers.UserService.service.UserService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/authenticate")
    public ResponseEntity<JwtResponse> authenticate(@RequestBody @NotNull JwtRequest request) throws Exception {
        return userService.authenticate(request);
    }

    @PostMapping("/save")
    public ResponseEntity<HttpResponse> save(@RequestBody @NotNull UserDTO userDTO)  {
        return userService.save(userDTO);
    }

}
