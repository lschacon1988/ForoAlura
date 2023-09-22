package com.alura.foro.APIRest.DTO.topic;

import com.alura.foro.APIRest.DTO.Status;
import com.alura.foro.APIRest.DTO.course.CourseResponseDTO;
import com.alura.foro.APIRest.DTO.user.UserDTO;
import com.alura.foro.APIRest.entity.Response;
import com.alura.foro.APIRest.entity.Topic;

import java.time.LocalDateTime;
import java.util.List;

public record TopicResponseDTO(
        Long id,
        String message,
        String title,
        Status status,
        LocalDateTime date



) {

    public TopicResponseDTO(Topic topic) {
        this(
                topic.getId(),
                topic.getMessage(),
                topic.getTitle(),
                topic.getStatus(),
                topic.getDate()



        );

    }
}
