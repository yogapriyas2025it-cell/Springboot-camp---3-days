package com.example.Springboot.Service;

import com.example.Springboot.Model.Student;
import com.example.Springboot.Repo.StudentRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepo repo;

    public StudentService(StudentRepo repo) {
        this.repo = repo;
    }

    // CREATE
    public Student addStudent(Student student) {
        return repo.save(student);
    }

    // READ ALL
    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    // READ ONE
    public Student getStudentById(Long id) {
        return repo.findById(id).orElse(null);
    }

    // UPDATE
    public Student updateStudent(Long id, Student student) {

        Student existing = repo.findById(id).orElse(null);

        if (existing != null) {
            existing.setName(student.getName());
            existing.setEmail(student.getEmail());
            existing.setDepartment(student.getDepartment());

            return repo.save(existing);
        }

        return null;
    }

    // DELETE
    public String deleteStudent(Long id) {

        if (repo.existsById(id)) {
            repo.deleteById(id);
            return "Student deleted successfully";
        }

        return "Student not found";
    }
}