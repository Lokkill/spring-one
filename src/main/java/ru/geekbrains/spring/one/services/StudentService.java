package ru.geekbrains.spring.one.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.one.model.Student;
import ru.geekbrains.spring.one.repositories.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Optional<Student> findOneById(Long id) {
        return studentRepository.findOneById(id);
    }

    public void save(Student student) {
        studentRepository.save(student);
    }

    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

    public double getAverageScore() {
        return studentRepository.findAll().stream().mapToInt(Student::getScore).average().getAsDouble();
    }

    public void incrementScore(Long id) {
        Student student = studentRepository.findOneById(id).get();
        if (student.getScore() < 100) {
            student.setScore(student.getScore() + 1);
        }
    }

    public void decrementScore(Long id) {
        Student student = studentRepository.findOneById(id).get();
        if (student.getScore() > 0) {
            student.setScore(student.getScore() - 1);
        }
    }

}
