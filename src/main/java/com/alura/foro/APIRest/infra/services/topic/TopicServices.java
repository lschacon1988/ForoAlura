package com.alura.foro.APIRest.infra.services.topic;

import com.alura.foro.APIRest.DTO.Status;
import com.alura.foro.APIRest.DTO.topic.TopicRequestDTO;
import com.alura.foro.APIRest.DTO.topic.TopicResponseDTO;
import com.alura.foro.APIRest.entity.Topic;
import com.alura.foro.APIRest.entity.User;
import com.alura.foro.APIRest.repository.CourseRepository;
import com.alura.foro.APIRest.repository.TopicsRepository;
import com.alura.foro.APIRest.repository.UsersRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class TopicServices {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private  CourseRepository courseRepository;
    @Autowired
    private  TopicsRepository topicsRepository;
    Status status;


    public TopicResponseDTO create(TopicRequestDTO datos) {
        var user = usersRepository.findByUsername(datos.username());
        System.out.println(user);
        var course = courseRepository.findByTitle(datos.titleCourse());

        Topic topic = new Topic();
        topic.setMessage(datos.message());
        topic.setTitle(datos.title());
        topic.setAutor((User) user);
        topic.setCurso(course);
        topic.setStatus(Status.NO_SOLUCIONADO);
        TopicResponseDTO newTopic = new TopicResponseDTO(topicsRepository.save(topic));
        return newTopic;
    }

}
