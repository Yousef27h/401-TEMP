package com.example.relationships;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void createStudentWorksThroughAllLayers() throws Exception {
        Student student = new Student("Jeff", "Bezos");

        mockMvc.perform(post("/students")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isCreated());

        Student foundStudent = studentRepository.findById(1L).orElseThrow();
        Assertions.assertEquals(student.getFirstName(), foundStudent.getFirstName());
    }
}
