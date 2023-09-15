package com.alura.foro.APIRest.controller;

import com.alura.foro.APIRest.DTO.user.DetailUserDTO;
import com.alura.foro.APIRest.DTO.user.RequestUserDTO;
import com.alura.foro.APIRest.DTO.user.UserDTO;
import com.alura.foro.APIRest.entity.User;
import com.alura.foro.APIRest.infra.errors.ErrorMessage;
import com.alura.foro.APIRest.infra.utils.UriComponenrs;
import com.alura.foro.APIRest.repository.UsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/users")
public class UsersControllers {

    private final UsersRepository usersRepository;

    public UsersControllers(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @GetMapping
    public ResponseEntity<Page<UserDTO>> getAllUser(@PageableDefault(size = 5) Pageable page) {


        Page<UserDTO> allUser = usersRepository.findAll(page).map(UserDTO::new);
        return ResponseEntity.ok(allUser);
    }

    @GetMapping("{id}")
    public ResponseEntity<DetailUserDTO> detailUser(@PathVariable Long id) {
        DetailUserDTO detailUser = new DetailUserDTO(usersRepository.getReferenceById(id));
        return ResponseEntity.ok(detailUser);

    }

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody RequestUserDTO user,
                                                    UriComponentsBuilder uriComponentsBuilder) {
        DetailUserDTO userExiset = usersRepository.findByEmail(user.email());
        if(userExiset != null){
            ErrorMessage message = new ErrorMessage();
            message.setMessage("El recurso que intenta registrar ya existe " + userExiset.toString());
            message.setStatus(409);
            return  ResponseEntity.status(409).body(message);
        }

        User userDb = new User();
        userDb.setEmail(user.email());
        userDb.setPassword(userDb.hashPassword(user.password()));
        userDb.setUsername(user.username());

        User newUser = usersRepository.save(userDb);
        URI url =
                UriComponenrs.buildUri(uriComponentsBuilder, newUser.getId(), "users");
        return ResponseEntity.created(url).body(new DetailUserDTO(newUser));
    }

    @PatchMapping("{id}")
    @Transactional
    public ResponseEntity<DetailUserDTO> updateUser(@RequestBody RequestUserDTO userUpdate,
                                                    @PathVariable Long id) {

        User userExist = usersRepository.getReferenceById(id);

        String newPassword = userUpdate.password().isEmpty() ?
                userExist.getPassword() : userUpdate.password();

        String newUsername = userUpdate.username().isEmpty() ?
                userExist.getUsername() : userUpdate.username();

        String newEmail = userUpdate.email().isEmpty() ?
                userExist.getEmail() : userUpdate.email();

        userExist.setUsername(newUsername);
        userExist.setPassword(userExist.hashPassword(newPassword));
        userExist.setEmail(newEmail);

        return ResponseEntity.ok(new DetailUserDTO(userExist));

    }

    @DeleteMapping("{id}")
    @Transactional
    public  ResponseEntity<Void> deleteUser(@PathVariable Long id){
        User userExist = usersRepository.getReferenceById(id);
        userExist.desactivar();

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/activar/{id}")
    @Transactional
    public ResponseEntity<DetailUserDTO> activarUser(@PathVariable Long id){
        User userDesactivado = usersRepository.getReferenceById(id);
        userDesactivado.activar();

        return  ResponseEntity.ok(new DetailUserDTO(userDesactivado));
    }
}
