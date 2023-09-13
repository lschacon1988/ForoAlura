package com.alura.foro.APIRest.repository;

import com.alura.foro.APIRest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Long> {
}
