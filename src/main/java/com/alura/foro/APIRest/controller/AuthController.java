package com.alura.foro.APIRest.controller;

import com.alura.foro.APIRest.DTO.user.UserLoginDTO;
import com.alura.foro.APIRest.entity.User;
import com.alura.foro.APIRest.infra.services.JWTTokenDTO;
import com.alura.foro.APIRest.infra.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/login")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    private ResponseEntity<JWTTokenDTO> login(@RequestBody UserLoginDTO userLoginDTO){
        System.out.println(userLoginDTO);
        Authentication authtoken = new UsernamePasswordAuthenticationToken(userLoginDTO.username(),
                userLoginDTO.password());

        var userSuth = authenticationManager.authenticate(authtoken);

        String JWTToken = tokenService.generateToken((User) userSuth.getPrincipal());
        return ResponseEntity.ok(new JWTTokenDTO(JWTToken));
    }
}
