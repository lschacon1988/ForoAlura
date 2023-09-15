package com.alura.foro.APIRest.infra.errors;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ErrorHandler {
    private final ErrorMessage errorMessage = new ErrorMessage();

    @ExceptionHandler(Exception.class)
    public ResponseEntity Error500(Exception e){
        this.errorMessage.setMessage("internal server Error " + e.getMessage());
        this.errorMessage.setStatus(500);
        return ResponseEntity.internalServerError().body(this.errorMessage);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> tratarError404() {
         this.errorMessage.setMessage("Recurso no encontado");
         this.errorMessage.setStatus(404);

        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }



}
