package com.alura.foro.APIRest.repository;

import com.alura.foro.APIRest.DTO.user.DetailUserDTO;
import com.alura.foro.APIRest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsersRepository extends JpaRepository<User, Long> {
    DetailUserDTO findByEmail(String email);

    UserDetails findByUsername(String username);
}
