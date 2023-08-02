package com.example.hogwarts32.service;

import com.example.hogwarts32.exception.FacultyNotFoundException;
import com.example.hogwarts32.exception.StudentNotFoundException;
import com.example.hogwarts32.model.Faculty;
import com.example.hogwarts32.model.Student;
import com.example.hogwarts32.repository.FacultyRepository;
import org.springframework.stereotype.Service;
import com.example.hogwarts32.repository.StudentRepository;

import java.util.Collection;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;

    public StudentService(StudentRepository studentRepository, FacultyRepository facultyRepository) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
    }


    public Student create(Student student) {
        return studentRepository.save(student);
    }

    public Student update(Long id, Student student) {
        Student existingStudent = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        existingStudent.setAge(student.getAge());
        existingStudent.setName(student.getName());
        return studentRepository.save(existingStudent);
    }

    public Student getById(Long id) {
        return studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);

    }

    public Collection<Student> getAll() {
        return studentRepository.findAll();
    }

    public Student remove(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        studentRepository.delete(student);
        return student;
    }

    public Collection<Student> getAllByAge(int age) {
        return studentRepository.findAllByAge(age);
    }

    public Collection<Student> getByAge(int min, int max) {
        return studentRepository.findOfByAgeBetween(min, max);
    }

    public Collection<Student> getByFacultyId(Long facultyId){
        return facultyRepository.findById(facultyId).map(Faculty::getStudents).orElseThrow(FacultyNotFoundException::new);
    }
}