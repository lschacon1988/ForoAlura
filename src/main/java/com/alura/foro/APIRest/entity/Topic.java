package com.alura.foro.APIRest.entity;

import com.alura.foro.APIRest.DTO.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "topics")
@Data
@AllArgsConstructor @NoArgsConstructor
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String message;
    private LocalDateTime date= LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private User autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Course curso;
    private Boolean activo = true;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true )
    private List<Response> response;

    public void addResponse(Response response){
        if(this.response == null){
            this.response = new ArrayList<>();
        }
        response.setTopic(this);
        this.response.add(response);
    }

    public void deactivate() {
        this.activo = false;
    }
    public void activate() {
        this.activo = true;
    }
}
