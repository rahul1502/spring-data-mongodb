package com.example.springdatamongodb;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/get")
    public List<Student> fetchAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping(value = "/put", consumes = "application/json")
    public void insertStudent(@RequestBody Student student) {
        studentService.insertStudent(student);
    }

}
