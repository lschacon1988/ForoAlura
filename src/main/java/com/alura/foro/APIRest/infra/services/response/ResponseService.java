package com.alura.foro.APIRest.infra.services.response;

import com.alura.foro.APIRest.DTO.response.ResponseDetailDTO;
import com.alura.foro.APIRest.DTO.response.ResponseRequestDTO;
import com.alura.foro.APIRest.DTO.user.UserDTO;
import com.alura.foro.APIRest.entity.Response;
import com.alura.foro.APIRest.entity.Topic;
import com.alura.foro.APIRest.entity.User;
import com.alura.foro.APIRest.infra.errors.IntegrityValidation;
import com.alura.foro.APIRest.repository.ResponseRepository;
import com.alura.foro.APIRest.repository.TopicsRepository;
import com.alura.foro.APIRest.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ResponseService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private TopicsRepository topicsRepository;

    @Autowired
    private ResponseRepository responseRepository;


    public ResponseDetailDTO createResponse(ResponseRequestDTO datos, Topic topic){
        var user = usersRepository.findByUsername(datos.username());


        if(user == null){
            throw new IntegrityValidation("El usuario no esta registrado");
        }

        if(topic == null){
            throw new IntegrityValidation("El id del topico no se encontro");
        }

        Response newResponse = new Response();
        newResponse.setMessage(datos.message());
        newResponse.setAutor((User) user);
        newResponse.setTopic(topic);
        topic.addResponse(newResponse);

        return new ResponseDetailDTO(newResponse);
    }


    public List<ResponseDetailDTO> responseTopic(Long idTopic){

        List<ResponseDetailDTO> response =
                responseRepository.findAll().stream().map(ResponseDetailDTO::new).toList();
        return response;
    }
}
