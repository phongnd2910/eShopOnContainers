package com.eshopcontainers.UserService.api;

import com.eshopcontainers.UserService.dto.UserDTO;
import com.eshopcontainers.UserService.model.HttpResponse;
import com.eshopcontainers.UserService.model.JwtRequest;
import com.eshopcontainers.UserService.model.JwtResponse;
import com.eshopcontainers.UserService.service.UserService;
import com.sun.istack.NotNull;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping(value = "/api/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/authenticate")
    public ResponseEntity<JwtResponse> authenticate(@RequestBody @NotNull JwtRequest request) throws Exception {
        return userService.authenticate(request);
    }

    @PostMapping("/save")
    public ResponseEntity<HttpResponse> save(@RequestBody @NotNull UserDTO userDTO) {
        return userService.save(userDTO);
    }

    @PostMapping("/test")
    public ResponseEntity<HttpResponse> test() {
        System.out.println("12313213");
        System.out.println("12313213");
        System.out.println("12313213");
        System.out.println("12313213");
        System.out.println("12313213");
        System.out.println("12313213");
        return null;
    }

    @GetMapping("/refreshToken")
    public ResponseEntity<JwtResponse> refreshToken(HttpServletRequest request) throws Exception {
        DefaultClaims claims = (DefaultClaims) request.getAttribute("claims");
        return null;
    }

}
