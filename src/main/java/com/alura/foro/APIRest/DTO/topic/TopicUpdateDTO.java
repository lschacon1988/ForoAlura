package com.alura.foro.APIRest.DTO.topic;

import com.alura.foro.APIRest.DTO.Status;
import com.alura.foro.APIRest.entity.Topic;
import jakarta.validation.constraints.NotNull;

public record TopicUpdateDTO(
        @NotNull
        String message,
        @NotNull
        String title,
        @NotNull
        Status status) {

    public TopicUpdateDTO(Topic topic) {
        this(

                topic.getMessage(),
                topic.getTitle(),
                topic.getStatus()
        );

    }
}
