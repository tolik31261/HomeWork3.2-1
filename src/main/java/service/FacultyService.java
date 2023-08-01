package service;

import exception.FacultyAlreadyExistsExeption;
import exception.FacultyNotFoundException;
import model.Faculty;
import org.springframework.stereotype.Service;
import repository.FacultyRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
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
}