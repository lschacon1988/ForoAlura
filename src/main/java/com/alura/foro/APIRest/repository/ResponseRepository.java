package com.alura.foro.APIRest.repository;

import com.alura.foro.APIRest.entity.Response;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponseRepository extends JpaRepository<Response, Long> {
}
