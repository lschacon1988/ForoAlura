package com.alura.foro.APIRest.infra.errors;

public class IntegrityValidation extends RuntimeException {
    public IntegrityValidation(String s){
        super(s);
    }
}
