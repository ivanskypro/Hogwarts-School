package ru.hogwarts.hogwartsschool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.hogwartsschool.model.Student;
import ru.hogwarts.hogwartsschool.repositories.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student find(long id) {
        return studentRepository.findById(id).get();
    }

    public Student create(Student student) {
      return studentRepository.save(student);
    }

    public Student update(Student student) {
        return studentRepository.save(student);
    }

    public void delete(long id) {
        studentRepository.deleteById(id);
    }

    public Collection<Student> print (){
        return studentRepository.findAll();}

   public Collection<Student> filter(int age) {
        return print().stream()
                .filter(student -> student.getAge() == age)
                .collect(Collectors.toList());
    }

    public Collection<Student> findByAgeBetween(int min, int max) {
        return studentRepository.findByAgeBetween(min, max);
    }

    public Integer quantityOfStudents(){
        return studentRepository.quantityOfStudents();
    }

    public Integer averageAgeOfStudents(){
       return studentRepository.averageAgeOfStudents();
    }

    public List<Student> getLastStudents(int count) {
        return studentRepository.getLastStudents(count);

    }



}
