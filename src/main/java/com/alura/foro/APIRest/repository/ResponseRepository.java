package com.alura.foro.APIRest.repository;

import com.alura.foro.APIRest.DTO.response.ResponseDetailDTO;
import com.alura.foro.APIRest.entity.Response;
import com.alura.foro.APIRest.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResponseRepository extends JpaRepository<Response, Long> {
    List<ResponseDetailDTO> findByTopic(Topic topic);

    @Query("SELECT r FROM Response r WHERE r.topic.id = :idTopic")
    List<ResponseDetailDTO> findAllByTopic(@Param("idTopic") long idTopic);
}
