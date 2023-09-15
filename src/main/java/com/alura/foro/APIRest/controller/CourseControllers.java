package com.alura.foro.APIRest.controller;

import com.alura.foro.APIRest.DTO.course.CourseRequestDTO;
import com.alura.foro.APIRest.DTO.course.CourseResponseDTO;
import com.alura.foro.APIRest.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/course")
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
    public ResponseEntity createCourse(@RequestBody CourseRequestDTO courseRequestDTO){
        return  null;
    }
}
