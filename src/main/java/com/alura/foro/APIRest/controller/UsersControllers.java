package com.alura.foro.APIRest.controller;

import com.alura.foro.APIRest.DTO.user.DetailUserDTO;
import com.alura.foro.APIRest.DTO.user.RequestUserDTO;
import com.alura.foro.APIRest.DTO.user.UserDTO;
import com.alura.foro.APIRest.entity.User;
import com.alura.foro.APIRest.infra.errors.ErrorMessage;
import com.alura.foro.APIRest.infra.utils.UriComponenrs;
import com.alura.foro.APIRest.repository.UsersRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
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
    @SecurityRequirement(name = "bearer-key")
    @Operation(summary = "Obtiene un listado de registros del usuarios",
            description = "Devuelbe un listado de todos los usuarios en la base de datos" +
                    "con un maximo de 5 registro por pagina, esta ruta se encuentra protegida con" +
                    " una" +
                    "estrategia de" +
                    " JWT", tags = "USERS")
    public ResponseEntity<Page<UserDTO>> getAllUser(@PageableDefault(size = 5, sort = "username") Pageable page
                                                     ) {


        Page<UserDTO> allUser = usersRepository.findByActivoTrue(page);
        return ResponseEntity.ok(allUser);
    }

    @GetMapping("{id}")
    @SecurityRequirement(name = "bearer-key")
    @Operation(summary = "Obtiene informacion detallada de registro del usuario",
            description = "Obten la informacion de un usuario en la base de datos" +
                    "consultando su id de registro, esta ruta se encuentra protegida con una " +
                    "estrategia de" +
                    " JWT", tags = "USERS")
    public ResponseEntity<DetailUserDTO> detailUser(@PathVariable Long id) {
        DetailUserDTO detailUser = new DetailUserDTO(usersRepository.getReferenceById(id));
        return ResponseEntity.ok(detailUser);

    }

    @PostMapping
    @Operation(summary = "Registra un usuario",
            description = "Registra credenciales de usuarios para acceder a todas las rutas " +
                    "protegidas", tags = "USERS")
    public ResponseEntity<Object> createUser(@RequestBody RequestUserDTO user,
                                             UriComponentsBuilder uriComponentsBuilder) {
        DetailUserDTO userExiset = usersRepository.findByEmail(user.email());
        if (userExiset != null) {
            ErrorMessage message = new ErrorMessage();
            message.setMessage("El recurso que intenta registrar ya existe " + userExiset.toString());
            message.setStatus(409);
            return ResponseEntity.status(409).body(message);
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
    @SecurityRequirement(name = "bearer-key")
    @Operation(summary = "Actualiza un usuario",
            description = "Actualiza las credenciales de usuarios para acceder a todas las rutas " +
                    "protegidas el usuario debe estar registrado, la ruta se encuentra" +
                    "protegida con una startegia JWT", tags =
            "USERS")
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
    @SecurityRequirement(name = "bearer-key")
    @Operation(summary = "Desactiva un usuario",
            description = "Esta ruta se encarga de realizar una desactivacion de usurios, la ruta" +
                    " se encuentra" +
                    "protegida con una startegia JWT", tags =
            "USERS")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        User userExist = usersRepository.getReferenceById(id);
        userExist.desactivar();

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/activar/{id}")
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    @Operation(summary = "Activar un usuario",
            description = "Esta ruta se encarga de realizar una Reactivacion de usurios, la ruta" +
                    " se encuentra" +
                    "protegida con una startegia JWT", tags =
            "USERS")
    public ResponseEntity<DetailUserDTO> activarUser(@PathVariable Long id) {
        User userDesactivado = usersRepository.getReferenceById(id);
        userDesactivado.activar();

        return ResponseEntity.ok(new DetailUserDTO(userDesactivado));
    }
}
