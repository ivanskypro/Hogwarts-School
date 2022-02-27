package ru.hogwarts.hogwartsschool.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.hogwartsschool.model.Faculty;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private final Map<Long, Faculty> list = new HashMap<>();
    private long id = 0;

    public Faculty find(long id) {
        if (list.containsKey(id)) {
            return list.get(id);
        }
        return null;
    }

    public Faculty create(Faculty faculty) {
        faculty.setId(++id);
        list.put(id, faculty);
        return faculty;
    }

    public Faculty update(Faculty faculty) {
        if (list.containsKey(faculty.getId())) {
            list.put(faculty.getId(), faculty);
            return faculty;
        }
        return null;
    }

    public Faculty delete(long id) {
        if (list.containsKey(id)) {
            return list.remove(id);
        }
        return null;
    }

    public Collection<Faculty> print (){
        return list.values();
    }



    public Collection<Faculty> filter(String color) {
        return list.values().stream()
                .filter(faculty -> faculty.getColor().equals(color))
                .collect(Collectors.toList());
    }

}
