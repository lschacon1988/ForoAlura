package com.alura.foro.APIRest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "respuesta")
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
    private Topics topico;
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    @OneToMany
    private User autor;
    private Boolean solucion = false;
}
