package ru.hogwarts.hogwartsschool.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.hogwartsschool.model.Faculty;
import ru.hogwarts.hogwartsschool.repositories.FacultyRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class FacultyService {


    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty find(long id) {
       return facultyRepository.findById(id).get();
    }

    public Faculty create(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty update(Faculty faculty) {
        return facultyRepository.save(faculty);
        }

    public void delete(long id) {
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> print (){
        return facultyRepository.findAll();
    }

    public Collection<Faculty> filter(String color) {
        return print().stream()
                .filter(faculty -> faculty.getColor().equals(color))
                .collect(Collectors.toList());
    }

}
