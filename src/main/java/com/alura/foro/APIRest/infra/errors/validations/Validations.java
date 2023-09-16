package com.alura.foro.APIRest.infra.errors.validations;

import com.alura.foro.APIRest.DTO.topic.TopicRequestDTO;

public interface Validations {
    public void validar(TopicRequestDTO datos);
}
