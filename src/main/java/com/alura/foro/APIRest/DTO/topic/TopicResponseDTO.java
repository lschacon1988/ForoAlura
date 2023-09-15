package com.alura.foro.APIRest.DTO.topic;

import com.alura.foro.APIRest.DTO.Status;
import com.alura.foro.APIRest.entity.Topic;

public record TopicResponseDTO(Long id, String message, String title, Status status) {

    public TopicResponseDTO(Topic topic) {
        this(
                topic.getId(),
                topic.getMessage(),
                topic.getTitle(),
                topic.getStatus()
        );

    }
}
