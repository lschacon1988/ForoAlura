package com.alura.foro.APIRest.controller;

import com.alura.foro.APIRest.DTO.course.CourseRequestDTO;
import com.alura.foro.APIRest.DTO.course.CourseResponseDTO;
import com.alura.foro.APIRest.DTO.course.CourseUpdateDTO;
import com.alura.foro.APIRest.DTO.user.DetailUserDTO;
import com.alura.foro.APIRest.entity.Course;
import com.alura.foro.APIRest.infra.errors.ErrorMessage;
import com.alura.foro.APIRest.infra.utils.UriComponenrs;
import com.alura.foro.APIRest.repository.CourseRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/courses")
@AllArgsConstructor
public class CourseControllers {

    private final CourseRepository courseRepository;

    @GetMapping
    public ResponseEntity<Page<CourseResponseDTO>> getAllCourse(@PageableDefault(size = 5)Pageable pageable){
        Page<CourseResponseDTO> allCourse =
                courseRepository.findAll(pageable).map(CourseResponseDTO::new);
        return ResponseEntity.ok(allCourse);
    }

    @GetMapping("{id}")
    public ResponseEntity<CourseResponseDTO> detailCourse(@PathVariable Long id){
        CourseResponseDTO detailCourse = new CourseResponseDTO(courseRepository.getReferenceById(id));
        return ResponseEntity.ok(detailCourse);
    }

    @PostMapping
    public ResponseEntity<Object> createCourse(@RequestBody @Valid CourseRequestDTO courseRequestDTO,
                                                          UriComponentsBuilder uriComponentsBuilder){

        Course courseExiset = courseRepository.findByTitle(courseRequestDTO.title());
        if(courseExiset != null){
            ErrorMessage message = new ErrorMessage();
            message.setMessage("El recurso que intenta registrar ya existe " + courseExiset.toString());
            message.setStatus(409);
            return  ResponseEntity.status(409).body(message);
        }

        Course course = new Course();
        course.setTitle(courseRequestDTO.title());
        course.setCategory(courseRequestDTO.category());

        Course newCourse = courseRepository.save(course);
        URI url = UriComponenrs.buildUri(uriComponentsBuilder, newCourse.getId(), "course");

        return  ResponseEntity.created(url).body(new CourseResponseDTO(newCourse));
    }

    @PatchMapping("{id}")
    @Transactional
    public  ResponseEntity<CourseUpdateDTO> updateCourse(@RequestBody @Valid CourseRequestDTO courseRequestDTO,
                                                             @PathVariable long id){

        Course course = courseRepository.getReferenceById(id);
        String newTitle = courseRequestDTO.title().isEmpty() ?
                course.getTitle() : courseRequestDTO.title();

        String newCategory = courseRequestDTO.category().isEmpty() ?
                course.getCategory() : courseRequestDTO.category();

        course.setTitle(newTitle);
        course.setCategory(newCategory);

        return ResponseEntity.ok(new CourseUpdateDTO(course));
    }

    @DeleteMapping("/deactivate/{id}")
    @Transactional
    public ResponseEntity<Void> deactivateCourse(@PathVariable Long id){
           Course course = courseRepository.getReferenceById(id);
           course.desactivar();

           return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/activater/{id}")
    @Transactional
    public ResponseEntity<Void> activateCourse(@PathVariable Long id){
        Course course = courseRepository.getReferenceById(id);
        course.activar();

        return ResponseEntity.noContent().build();
    }
}
