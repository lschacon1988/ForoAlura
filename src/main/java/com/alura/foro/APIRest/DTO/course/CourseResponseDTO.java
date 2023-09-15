package com.alura.foro.APIRest.DTO.course;

import com.alura.foro.APIRest.entity.Course;

public record CourseResponseDTO(Long id, String title, String category) {

    public CourseResponseDTO(Course course) {
        this(course.getId(), course.getTitle(), course.getCategory());
    }
}
