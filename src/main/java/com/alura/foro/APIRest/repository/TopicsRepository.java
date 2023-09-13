package com.alura.foro.APIRest.repository;

import com.alura.foro.APIRest.entity.Topics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicsRepository extends JpaRepository<Topics, Long> {
}
