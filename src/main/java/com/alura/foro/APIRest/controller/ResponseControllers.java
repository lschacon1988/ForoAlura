package com.alura.foro.APIRest.controller;


import com.alura.foro.APIRest.DTO.response.ResponseDetailDTO;
import com.alura.foro.APIRest.DTO.response.ResponseRequestDTO;
import com.alura.foro.APIRest.entity.Topic;
import com.alura.foro.APIRest.infra.services.response.ResponseService;
import com.alura.foro.APIRest.infra.utils.UriComponenrs;
import com.alura.foro.APIRest.repository.ResponseRepository;
import com.alura.foro.APIRest.repository.TopicsRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/responses")
public class ResponseControllers {

    @Autowired
    private ResponseRepository responseRepository;

    @Autowired
    private ResponseService responseService;
    @Autowired
    private TopicsRepository topicsRepository;


    @GetMapping("{idTopic}")
    @SecurityRequirement(name = "bearer-key")
    @Transactional
    public ResponseEntity<List<ResponseDetailDTO>> getResponseTopic(@PathVariable Long idTopic
                                                                    ){

        List<ResponseDetailDTO> responses = responseService.responseTopic(idTopic);
        return ResponseEntity.ok(responses);
    }

    @PostMapping("{idTopic}")
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<ResponseDetailDTO> responseTopic(@PathVariable long idTopic,
                                                           @RequestBody ResponseRequestDTO responseRequestDTO,
                                                           UriComponentsBuilder uriComponentsBuilder){
        Topic topic = topicsRepository.getReferenceById(idTopic);

        URI url = UriComponenrs.buildUri(uriComponentsBuilder, idTopic,"responses");

        return ResponseEntity.created(url).body(responseService.createResponse(responseRequestDTO
                ,topic));

    }
}
