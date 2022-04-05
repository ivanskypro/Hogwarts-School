package ru.hogwarts.hogwartsschool.service;

import liquibase.pro.packaged.F;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.hogwartsschool.model.Faculty;
import ru.hogwarts.hogwartsschool.repositories.FacultyRepository;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    Logger logger = LoggerFactory.getLogger(FacultyService.class);
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty find(long id) {
       logger.info("Method for finding faculty was invoked");
        return facultyRepository.findById(id).get();
    }

    public Faculty create(Faculty faculty) {
        logger.info("Method for creating faculty was invoked");
        return facultyRepository.save(faculty);
    }

    public Faculty update(Faculty faculty) {
        logger.info("Method for updating faculty was invoked");
        return facultyRepository.save(faculty);
        }

    public void delete(long id) {
        logger.info("Method for deleting faculty was invoked");
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> print (){
        logger.info("Method for printing faculty was invoked");
        return facultyRepository.findAll();
    }

    public Collection<Faculty> filterByColor (String color) {
        logger.info("Method for filtering faculty by color was invoked");
        return facultyRepository.findByColorIgnoreCase(color);
    }

    public Collection<Faculty> filterByName(String name) {
        logger.info("Method for filtering faculty by name was invoked");
        return facultyRepository.findFacultiesByNameIgnoreCase(name);
    }

    public String getTheLongestFacultyName(){
        logger.info("Method for finding the longest faculty name was invoked");
        List<Faculty> allFaculties = facultyRepository.findAll();
        return allFaculties.stream().
                parallel().
                reduce((a,b)->a.getName().length() > b.getName().length() ? a:b).
                toString();
    }

}
