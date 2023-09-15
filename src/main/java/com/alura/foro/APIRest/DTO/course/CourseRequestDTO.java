package com.alura.foro.APIRest.DTO.course;

import com.alura.foro.APIRest.entity.Course;

public record CourseRequestDTO(Long id,
                               String title,
                               String category) {

    public CourseRequestDTO(Course course) {
        this(course.getId(),
                course.getTitle(),
                course.getCategory()
        );
    }
}
