package service;

import exception.StudentAlreadyExistsExeption;
import exception.StudentNotFoundException;
import model.Student;
import org.springframework.stereotype.Service;
import repository.StudentRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    private static Long COUNTER = 0L;
    private final Map<Long, Student> storage = new HashMap<>();

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
        return storage.get(id);

    }

    public Collection<Student> getAll() {
        return storage.values();
    }

    public Student remove(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        studentRepository.delete(student);
        return student;
    }

    public Collection<Student> getAllByAge(int age) {
        return studentRepository.findAllByAge(age);
    }
}