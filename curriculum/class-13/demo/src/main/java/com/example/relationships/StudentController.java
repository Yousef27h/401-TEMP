package com.example.relationships;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

//    @PostMapping("/students")
//    public ResponseEntity<Student> createStudent(String firstName, String lastName) {
//        Student savedStudent = studentRepository.save(new Student(firstName, lastName));
//        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
//    }

    @PostMapping("/students")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student savedStudent = studentRepository.save(student);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    @PostMapping("/students/{id}/departments")
    public ResponseEntity<Student> addStudentDepartment(@PathVariable Long id, Long departmentId) {
        Department department = departmentRepository.findById(departmentId).orElseThrow();

        Student student = studentRepository.findById(id).orElseThrow();
        student.setDepartment(department);

        return new ResponseEntity<>(studentRepository.save(student), HttpStatus.OK);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getOneStudent(@PathVariable Long id) {
        Student returnedStudent = studentRepository.findById(id).orElseThrow();
        return new ResponseEntity<>(returnedStudent, HttpStatus.OK);
    }
}
