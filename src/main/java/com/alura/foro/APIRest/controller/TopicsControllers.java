package com.alura.foro.APIRest.controller;

import com.alura.foro.APIRest.DTO.topic.TopicRequestDTO;
import com.alura.foro.APIRest.DTO.topic.TopicResponseDTO;
import com.alura.foro.APIRest.DTO.topic.TopicUpdateDTO;
import com.alura.foro.APIRest.entity.Topic;
import com.alura.foro.APIRest.infra.errors.ErrorMessage;
import com.alura.foro.APIRest.infra.services.topic.TopicServices;
import com.alura.foro.APIRest.infra.utils.UriComponenrs;
import com.alura.foro.APIRest.repository.TopicsRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
    @Transactional
    @Operation(summary = "Obtiene los registros del topicos",
    description = "Obtine un listado de registro de topicos de la base de datos," +
            "paginados en 5 registros por paginas ",tags = "TOPICS")
    public ResponseEntity<Page<TopicResponseDTO>> getAllTopics(@PageableDefault(size = 5) Pageable pageable){
        Page<TopicResponseDTO> allTopics = topicsRepository.findAll(pageable).map(TopicResponseDTO:: new);

        return  ResponseEntity.ok(allTopics);
    }

    @GetMapping("{id}")
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    @Operation(summary = "Obtiene los detalles del topico",
            description = "Obtine la informacion detallada de un  registro en la base " +
                    "de datos," +
                    " el parametro de busqaueda es el id del topico ",tags = "TOPICS")
    public  ResponseEntity<TopicResponseDTO> detailTopic(@PathVariable Long id){
        Topic topic = topicsRepository.getReferenceById(id);

        return  ResponseEntity.ok(new TopicResponseDTO(topic));
    }

    @PostMapping
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    @Operation(summary = "Crea un registros del topico",
            description = "Crea un registro de topico en la base de datos, recibe la informacion " +
                    "en formato JSON, si el titulo del topico coninside con alguno existente " +
                    "devolvera 409",tags = "TOPICS")
    public  ResponseEntity<Object> createTopic(@RequestBody TopicRequestDTO topicRequestDTO,
                                                         UriComponentsBuilder uriComponentsBuilder){
        Boolean courseExiset = topicsRepository.existsByTitle(topicRequestDTO.title());

        if(courseExiset){
            ErrorMessage message = new ErrorMessage();
            message.setMessage("El recurso que intenta registrar ya existe " + courseExiset.toString());
            message.setStatus(409);

            return  ResponseEntity.status(409).body(message);
        }

        var newTopic = topicServices.create(topicRequestDTO);
        URI url = UriComponenrs.buildUri(uriComponentsBuilder, newTopic.id(),"topics");

        return ResponseEntity.created(url).body(newTopic);
    }

    @PatchMapping("{id}")
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    @Operation(summary = "Actualiza los registros del topicos",
            description = "Actualiza el topico qeu coincida con el id pasado como parametro de " +
                    "busqueda.",tags = "TOPICS")
    public ResponseEntity<TopicResponseDTO> updateTopic(@PathVariable Long id,
                                                        @RequestBody TopicUpdateDTO topicUpdateDTO){
        Topic topic = topicsRepository.getReferenceById(id);
        String newMessage = topicUpdateDTO.message().isEmpty() ?
                topic.getMessage() : topicUpdateDTO.message();
        String newTitle = topicUpdateDTO.title().isEmpty() ?
                topic.getTitle() : topicUpdateDTO.title();

        topic.setTitle(newTitle);
        topic.setMessage(newMessage);

        return ResponseEntity.ok(new TopicResponseDTO(topic));

    }

    @DeleteMapping("{id}")
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    @Operation(summary = "Desactiva los registros del topicos",
            description = "Hace un Borrado logico del topico seleccionado " +
                    "es decir no lo elimina de la base de datos solo lo desactiva",
    tags = "TOPICS")
    public ResponseEntity<Void> deactivateTopic(@PathVariable Long id){
        Topic topic = topicsRepository.getReferenceById(id);
        topic.deactivate();

        return ResponseEntity.noContent().build();
    }

}
