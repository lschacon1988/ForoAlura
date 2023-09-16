package com.alura.foro.APIRest.DTO.topic;

import com.alura.foro.APIRest.DTO.Status;
import com.alura.foro.APIRest.entity.Topic;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicResponseDTO(
        Long id,
        @NotNull @NotBlank
        String message,
        @NotNull @NotBlank
        String title,
        @NotNull @NotBlank
        Status status) {

    public TopicResponseDTO(Topic topic) {
        this(
                topic.getId(),
                topic.getMessage(),
                topic.getTitle(),
                topic.getStatus()
        );

    }
}
