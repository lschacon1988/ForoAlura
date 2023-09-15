package com.alura.foro.APIRest.repository;

import com.alura.foro.APIRest.DTO.course.CourseResponseDTO;
import com.alura.foro.APIRest.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
    CourseResponseDTO findByTitle(String title);
}
