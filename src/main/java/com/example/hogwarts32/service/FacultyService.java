package com.example.hogwarts32.service;

import com.example.hogwarts32.exception.FacultyNotFoundException;
import com.example.hogwarts32.exception.StudentNotFoundException;
import com.example.hogwarts32.model.Faculty;
import com.example.hogwarts32.model.Student;
import com.example.hogwarts32.repository.StudentRepository;
import org.springframework.stereotype.Service;
import com.example.hogwarts32.repository.FacultyRepository;

import java.util.Collection;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;
    private final StudentRepository studentRepository;

    public FacultyService(FacultyRepository facultyRepository, StudentRepository studentRepository) {
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
    }

    public Faculty getById(Long id) {
        return facultyRepository.findById(id).orElseThrow(FacultyNotFoundException::new);
    }

    public Faculty create(Faculty faculty) {
        return facultyRepository.save(faculty);
    }
    public Faculty update(Long id, Faculty faculty) {
        Faculty existingFaculty = facultyRepository.findById(id).orElseThrow(FacultyNotFoundException::new);
        existingFaculty.setColor(faculty.getColor());
        existingFaculty.setName(faculty.getName());
        return facultyRepository.save(existingFaculty);
    }

    public Collection<Faculty> getAll() {
        return facultyRepository.findAll();
    }

    public Faculty remove(Long id) {
        Faculty faculty = facultyRepository.findById(id).orElseThrow(FacultyNotFoundException::new);
        facultyRepository.delete(faculty);
        return faculty;
    }

    public Collection<Faculty> getAllByColor(String color) {
        return facultyRepository.findAllByColor(color);
    }

    public Collection<Faculty> getAllByNameOrColor(String name, String color) {
        return facultyRepository.findAllByColorLikeIgnoreCaseOOrNameLikeIgnoreCase(color, name);
    }

    public Faculty getByStudentId(Long studentId) {
        return studentRepository.findById(studentId).map(Student::getFaculty).orElseThrow(StudentNotFoundException::new);
    }
}