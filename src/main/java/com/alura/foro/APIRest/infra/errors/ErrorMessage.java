package com.alura.foro.APIRest.infra.errors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class ErrorMessage {
    private String message;
    private long status;
}
