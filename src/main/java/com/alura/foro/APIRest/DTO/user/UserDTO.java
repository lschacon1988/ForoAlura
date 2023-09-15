package com.alura.foro.APIRest.DTO.user;


import com.alura.foro.APIRest.entity.User;

public record UserDTO(Long id,
                      String username,
                      String email) {

    public UserDTO(User user) {
        this(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        );
    }

}
