package com.alura.foro.APIRest.controller;

import com.alura.foro.APIRest.DTO.user.UserLoginDTO;
import com.alura.foro.APIRest.entity.User;
import com.alura.foro.APIRest.infra.errors.ErrorMessage;
import com.alura.foro.APIRest.infra.errors.IntegrityValidation;
import com.alura.foro.APIRest.infra.services.security.JWTTokenDTO;
import com.alura.foro.APIRest.infra.services.security.TokenService;
import com.alura.foro.APIRest.repository.UsersRepository;
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
    private UsersRepository usersRepository;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    private ResponseEntity<JWTTokenDTO> login(@RequestBody UserLoginDTO userLoginDTO){

        Boolean userExist = usersRepository.existsByUsername(userLoginDTO.username());

        if(!userExist){
            throw new IntegrityValidation("El usuario no esta registrado");
            //            return ResponseEntity.badRequest().body(new ErrorMessage("El usuario no esta " +
//                    "registrado",400));
        }

        Authentication authtoken = new UsernamePasswordAuthenticationToken(userLoginDTO.username(),
                userLoginDTO.password());

        var userSuth = authenticationManager.authenticate(authtoken);

        String JWTToken = tokenService.generateToken((User) userSuth.getPrincipal());
        return ResponseEntity.ok(new JWTTokenDTO(JWTToken));
    }
}
