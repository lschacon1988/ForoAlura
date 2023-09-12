package com.alura.foro.APIRest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name= "topicos")
@Data
@AllArgsConstructor @NoArgsConstructor
public class Topics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String message;
    private LocalDateTime date= LocalDateTime.now();

    private String status;
    @ManyToOne(fetch = FetchType.LAZY)
    private User autor;
    @OneToOne(fetch = FetchType.LAZY)
    private Course curso;

    private List<Response> response;



}
