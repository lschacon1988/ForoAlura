package com.alura.foro.APIRest.DTO.topic;

import com.alura.foro.APIRest.entity.Topic;
import jakarta.validation.constraints.NotNull;

public record TopicUpdateDTO(
        @NotNull
        String message,
        @NotNull
        String title) {

    public TopicUpdateDTO(Topic topic) {
        this(

                topic.getMessage(),
                topic.getTitle()

        );

    }
}
