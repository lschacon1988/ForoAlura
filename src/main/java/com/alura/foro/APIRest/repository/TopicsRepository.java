package com.alura.foro.APIRest.repository;

import com.alura.foro.APIRest.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicsRepository extends JpaRepository<Topic, Long> {
}
