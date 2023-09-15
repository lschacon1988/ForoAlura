package com.alura.foro.APIRest.DTO.user;

import com.alura.foro.APIRest.entity.User;

import java.time.LocalDateTime;

public record DetailUserDTO(String username,
                            String email,
                            Long id,
                            LocalDateTime registered) {

    public DetailUserDTO(User user){
        this(
                user.getUsername(),
                user.getEmail(),
                user.getId(),
                user.getRegistered()
        );
    }
}
