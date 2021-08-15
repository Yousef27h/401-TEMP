package com.example.relationships;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @PostMapping("/courses")
    public ResponseEntity<Course> createCourse(String name, String prerequisite) {
        Course savedCourse = courseRepository.save(new Course(name, prerequisite));
        return new ResponseEntity<>(savedCourse, HttpStatus.CREATED);
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<Course> getOneCourse(@PathVariable Long id) {
        Course returnedCourse = courseRepository.findById(id).orElseThrow();
        return new ResponseEntity<>(returnedCourse, HttpStatus.OK);
    }
}
