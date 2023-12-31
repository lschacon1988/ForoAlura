package com.alura.foro.APIRest.infra.errors;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ErrorHandler {
    private final ErrorMessage errorMessage = new ErrorMessage();

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> Error500(Exception e){
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> tratarError400(MethodArgumentNotValidException e){
        var errores = e.getFieldErrors().stream().map(DatosErrorValidacion::new).toList();
        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(IntegrityValidation.class)
    public ResponseEntity<ErrorMessage> trataIntegridad(IntegrityValidation e){
        this.errorMessage.setMessage(e.getMessage());
        this.errorMessage.setStatus(400);
        return ResponseEntity.badRequest().body(this.errorMessage);
    }

    private record DatosErrorValidacion(String campo, String error){
        public DatosErrorValidacion(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }

}
