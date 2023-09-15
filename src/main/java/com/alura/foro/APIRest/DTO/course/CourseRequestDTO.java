package com.alura.foro.APIRest.DTO.course;

import com.alura.foro.APIRest.entity.Course;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CourseRequestDTO(
        @NotNull
        String title,
        @NotNull
        String category) {

    public CourseRequestDTO(Course course) {
        this(
                course.getTitle(),
                course.getCategory()
        );
    }
}
