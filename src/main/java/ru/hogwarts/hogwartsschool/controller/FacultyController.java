package ru.hogwarts.hogwartsschool.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.hogwarts.hogwartsschool.model.Faculty;
import ru.hogwarts.hogwartsschool.service.FacultyService;

import java.util.Collection;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("/{id}")
    public Faculty getFaculty(@PathVariable Long id) {
        Faculty faculty = facultyService.find(id);
        if (faculty == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return faculty;
    }

    @PostMapping
    public ResponseEntity<Faculty> addFaculty(@RequestBody Faculty faculty) {
        return ResponseEntity.ok(facultyService.create(faculty));
    }

    @PutMapping
    public ResponseEntity<Faculty> updateFaculty(@RequestBody Faculty faculty) {
        if (faculty == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id не найден");
        }
        return ResponseEntity.ok(facultyService.update(faculty));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteFaculty (@PathVariable Long id){
        facultyService.delete(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping ("/print")
    public Collection<Faculty> pintFaculties (){
        return facultyService.print();
    }

    @GetMapping("/filter/{color}")
    public ResponseEntity<Collection<Faculty>> filter(@PathVariable String color) {
        return ResponseEntity.ok(facultyService.filter(color));
    }
}
