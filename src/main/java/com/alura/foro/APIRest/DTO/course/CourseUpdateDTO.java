package com.alura.foro.APIRest.DTO.course;

import com.alura.foro.APIRest.entity.Course;
import jakarta.validation.constraints.NotNull;

public record CourseUpdateDTO(
        @NotNull
        String title,
        @NotNull
        String category) {

    public CourseUpdateDTO(Course course) {
        this(
                course.getTitle(),
                course.getCategory()
        );
    }
}
