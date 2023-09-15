package com.alura.foro.APIRest.controller;

import com.alura.foro.APIRest.DTO.topic.TopicResponseDTO;
import com.alura.foro.APIRest.repository.TopicsRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/topics")
@AllArgsConstructor
public class TopicsControllers {

    private final TopicsRepository topicsRepository;

    @GetMapping
    public ResponseEntity<Page<TopicResponseDTO>> getAllTopics(@PageableDefault(size = 5) Pageable pageable){
        Page<TopicResponseDTO> allTopics = topicsRepository.findAll(pageable).map(TopicResponseDTO:: new);
        return  ResponseEntity.ok(allTopics);
    }


}
