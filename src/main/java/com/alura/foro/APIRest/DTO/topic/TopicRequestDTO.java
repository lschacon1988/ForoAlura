package com.alura.foro.APIRest.DTO.topic;

import com.alura.foro.APIRest.DTO.Status;
import com.alura.foro.APIRest.entity.Course;
import com.alura.foro.APIRest.entity.Topic;
import com.alura.foro.APIRest.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicRequestDTO(
        @NotNull @NotBlank
        String message,
        @NotNull @NotBlank
        String title,
        @NotNull
        Status status,
        @NotNull
        String username,
        @NotNull
        String titleCourse) {

//    public TopicRequestDTO(Topic topic) {
//        this(
//
//                topic.getMessage(),
//                topic.getTitle(),
//                topic.getStatus(),
//                topic.getAutor().getUsername(),
//                topic.getCurso().getTitle()
//        );
//
//    }
}
