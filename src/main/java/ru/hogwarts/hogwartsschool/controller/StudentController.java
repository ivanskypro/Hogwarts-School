package ru.hogwarts.hogwartsschool.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.hogwarts.hogwartsschool.model.Student;
import ru.hogwarts.hogwartsschool.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Long id) {
        Student student = studentService.find(id);
        return student;
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.create(student));
    }

    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        if (student == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(studentService.update(student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteStudent (@PathVariable Long id){
       studentService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping ("/print")
    public Collection<Student> pintStudents (){
        return studentService.print();
    }


    @GetMapping("/filter/{age}")
    public ResponseEntity<Collection<Student>> filter(@PathVariable Integer age) {
        return ResponseEntity.ok(studentService.filter(age));
    }
}
