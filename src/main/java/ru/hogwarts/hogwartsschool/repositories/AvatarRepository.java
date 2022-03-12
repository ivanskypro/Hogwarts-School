package ru.hogwarts.hogwartsschool.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.hogwartsschool.model.Avatar;

import java.util.Optional;

public interface AvatarRepository extends JpaRepository<Avatar,Long> {
    Optional<Avatar> findById(Long studentId);
}
