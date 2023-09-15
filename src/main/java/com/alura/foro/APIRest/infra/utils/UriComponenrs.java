package com.alura.foro.APIRest.infra.utils;


import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class UriComponenrs {



    public static URI buildUri(UriComponentsBuilder uriComponentsBuilder, Long id, String nameRecurso){
        return uriComponentsBuilder.path("api/v1/"+ nameRecurso +"/{id}").buildAndExpand(id).toUri();
    }
}
