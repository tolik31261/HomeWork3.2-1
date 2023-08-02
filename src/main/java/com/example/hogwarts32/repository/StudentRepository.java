package com.example.hogwarts32.repository;

import com.example.hogwarts32.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository <Student, Long> {
    List<Student> findAllByAge(int age);
}
