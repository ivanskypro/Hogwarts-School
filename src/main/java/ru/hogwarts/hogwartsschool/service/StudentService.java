package ru.hogwarts.hogwartsschool.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.hogwartsschool.model.Student;
import ru.hogwarts.hogwartsschool.repositories.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StudentService {

    Logger logger = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student find(long id) {
        return studentRepository.findById(id).get();
    }

    public Student create(Student student) {
        logger.info("Method for creating student was invoked for student:{}",student);
        return studentRepository.save(student);
    }

    public Student update(Student student) {
        logger.info("Method for updating student was invoked for student:{}", student);
        return studentRepository.save(student);
    }

    public void delete(long id) {
        logger.info("Method for deleting student was invoked for student's id:{}", id);
        studentRepository.deleteById(id);
    }

    public Collection<Student> print (){
        logger.info("Method for printing all students was invoked");
        return studentRepository.findAll();}

   public Collection<Student> filter(int age) {
       logger.info("Method for filtering students by age was invoked");
        return print().stream()
                .filter(student -> student.getAge() == age)
                .collect(Collectors.toList());
    }

    public Collection<Student> findByAgeBetween(int min, int max) {
        logger.info("Method for finding students between age was invoked");
        return studentRepository.findByAgeBetween(min, max);
    }

    public Integer quantityOfStudents(){
        logger.info("Method for counting students was invoked");
        return studentRepository.quantityOfStudents();
    }

    //public Integer averageAgeOfStudents(){
    //    logger.info("Method for counting average students' age was invoked");
    //    return studentRepository.averageAgeOfStudents();
    //}

    public List<Student> getLastStudents(int count) {
        logger.info("Method for finding last students was invoked");
        return studentRepository.getLastStudents(count);
    }

    public List<String> getStudentsWithFirstALetter (){
        logger.info("Method for finding students with first A letter in the name was invoked");
        List<Student> allStudents = studentRepository.findAll();

        return allStudents.stream().
                parallel().
                map(Student::getName).
                filter(e->e.startsWith("A")).
                sorted().
                collect(Collectors.toList());
    }

    public double getAverageAgeOfStudents(){
        logger.info("Method for finding average students' age was invoked");
        List<Student> allStudents = studentRepository.findAll();
        return allStudents.stream().
                mapToDouble(Student::getAge).
                average().orElse(0.0);
    }

    public int getNumber(){
        logger.info("Method for getting some number was invoked");
        long start = System.currentTimeMillis();
        int sum = Stream.
                iterate(1, a -> a +1).
                limit(1_000_000).
                parallel().
                reduce(0, (a, b) -> a + b );
        System.out.println(System.currentTimeMillis() - start);
        return sum;

    }



}
