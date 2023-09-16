package com.alura.foro.APIRest.controller;

import com.alura.foro.APIRest.DTO.topic.TopicRequestDTO;
import com.alura.foro.APIRest.DTO.topic.TopicResponseDTO;
import com.alura.foro.APIRest.DTO.topic.TopicUpdateDTO;
import com.alura.foro.APIRest.entity.Topic;
import com.alura.foro.APIRest.infra.services.topic.TopicServices;
import com.alura.foro.APIRest.infra.utils.UriComponenrs;
import com.alura.foro.APIRest.repository.TopicsRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/topics")
@AllArgsConstructor
public class TopicsControllers {

    private final TopicsRepository topicsRepository;
    private  final TopicServices topicServices;

    @GetMapping
    public ResponseEntity<Page<TopicResponseDTO>> getAllTopics(@PageableDefault(size = 5) Pageable pageable){
        Page<TopicResponseDTO> allTopics = topicsRepository.findAll(pageable).map(TopicResponseDTO:: new);

        return  ResponseEntity.ok(allTopics);
    }

    @GetMapping("{id}")
    public  ResponseEntity<TopicResponseDTO> detailTopic(@PathVariable Long id){
        Topic topic = topicsRepository.getReferenceById(id);
        System.out.println(topic.getAutor().getUsername());
        return  ResponseEntity.ok(new TopicResponseDTO(topic));
    }

    @PostMapping
    public  ResponseEntity<TopicResponseDTO> createTopic(@RequestBody TopicRequestDTO topicRequestDTO,
                                                         UriComponentsBuilder uriComponentsBuilder){
            var newTopic = topicServices.create(topicRequestDTO);
        URI url = UriComponenrs.buildUri(uriComponentsBuilder, newTopic.id(),"topics");
            return ResponseEntity.created(url).body(newTopic);
    }

    @PatchMapping("{id}")
    @Transactional
    public ResponseEntity<TopicResponseDTO> updateTopic(@PathVariable Long id,
                                                        @RequestBody TopicUpdateDTO topicUpdateDTO){
        Topic topic = topicsRepository.getReferenceById(id);
        String newMessage = topicUpdateDTO.message().isEmpty() ?
                topic.getMessage() : topicUpdateDTO.message();
        String newTitle = topicUpdateDTO.title().isEmpty() ?
                topic.getTitle() : topicUpdateDTO.title();

        topic.setTitle("");
        topic.setMessage("");

        return ResponseEntity.ok(new TopicResponseDTO(topic));

    }


}
