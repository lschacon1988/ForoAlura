package com.alura.foro.APIRest.DTO.response;

import com.alura.foro.APIRest.DTO.topic.TopicResponseDTO;
import com.alura.foro.APIRest.DTO.user.UserDTO;
import com.alura.foro.APIRest.entity.Response;

public record ResponseDetailDTO(
        String message,
        TopicResponseDTO topic,
        UserDTO autor) {
    public ResponseDetailDTO(Response newResponse) {
        this(
                newResponse.getMessage(),
                new TopicResponseDTO(newResponse.getTopic()),
                new UserDTO(newResponse.getAutor())
        );
    }
}
