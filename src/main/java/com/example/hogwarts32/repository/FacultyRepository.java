package com.example.hogwarts32.repository;

import com.example.hogwarts32.model.Faculty;
import com.example.hogwarts32.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface FacultyRepository extends JpaRepository <Faculty, Long> {
 List<Faculty> findAllByColor(String color);
 List<Faculty> findAllByColorLikeIgnoreCaseOOrNameLikeIgnoreCase(String color, String name);

}
