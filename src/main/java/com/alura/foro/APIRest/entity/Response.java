package com.alura.foro.APIRest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "topic_response")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    @ManyToOne()
    @JoinColumn(name= "topico_id")
    private Topic topic;
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    @ManyToOne
    private User autor;
    private Boolean solucion = false;
}
