package ru.hogwarts.hogwartsschool.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.hogwartsschool.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final Map<Long, Student> list = new HashMap<>();
    private long id = 0;

    public Student find(long id) {
        if (list.containsKey(id)) {
            return list.get(id);
        }
        return null;
    }

    public Student create(Student student) {
        student.setId(++id);
        list.put(id, student);
        return student;
    }

    public Student update(Student student) {
        if (list.containsKey(student.getId())) {
            list.put(student.getId(), student);
            return student;
        }
        return null;
    }

    public Student delete(long id) {
        if (list.containsKey(id)) {
            return list.remove(id);
        }
        return null;
    }

    public Collection<Student> print (){
        return list.values();
    }

    public Collection<Student> filter(int age) {
        return list.values().stream()
                .filter(student -> student.getAge() == age)
                .collect(Collectors.toList());
    }

}
