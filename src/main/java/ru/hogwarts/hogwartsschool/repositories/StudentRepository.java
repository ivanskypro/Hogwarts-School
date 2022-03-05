package ru.hogwarts.hogwartsschool.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.hogwartsschool.model.Student;

public interface StudentRepository extends JpaRepository <Student, Long>{
}
