package com.alura.foro.APIRest.DTO.topic;

import com.alura.foro.APIRest.DTO.Status;

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


}
