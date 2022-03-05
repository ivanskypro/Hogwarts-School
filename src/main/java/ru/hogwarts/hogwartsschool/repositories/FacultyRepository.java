package ru.hogwarts.hogwartsschool.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.hogwartsschool.model.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
}
