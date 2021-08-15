package com.example.relationships;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DepartmentController {

    @Autowired
    private DepartmentRepository departmentRepository;

    @PostMapping("/departments")
    public ResponseEntity<Department> createStudent(String name) {
        Department savedDepartment = departmentRepository.save(new Department(name));
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    @GetMapping("/departments/{id}")
    public ResponseEntity<Department> getOneStudent(@PathVariable Long id) {
        Department returnedDepartment = departmentRepository.findById(id).orElseThrow();
        return new ResponseEntity<>(returnedDepartment, HttpStatus.OK);
    }
}
