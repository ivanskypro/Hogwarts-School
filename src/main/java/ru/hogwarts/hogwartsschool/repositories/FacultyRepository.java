package ru.hogwarts.hogwartsschool.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.hogwartsschool.model.Faculty;

import java.util.Collection;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    Collection<Faculty> findFacultiesByNameIgnoreCase (String name);

    Collection<Faculty> findByColorIgnoreCase(String color);

}
