package com.example.accesscontrolsystem.manager;

import com.example.accesscontrolsystem.mapper.StudentMapper;
import com.example.accesscontrolsystem.model.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("StudentManager")
public class StudentManager {
    private final StudentMapper studentMapper;
    @Autowired
    public StudentManager(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    public Optional<Student> findStudentById(Integer studentId) {
        return studentMapper.findStudentById(studentId);
    }

    public String getAuthById(Integer studentId) {
        Optional<Student> student = studentMapper.findStudentById(studentId);
        return student.map(Student::getAuth).orElse(null);
    }

    public String getNameById(Integer studentId) {
        Optional<Student> student = studentMapper.findStudentById(studentId);
        return student.map(Student::getName).orElse(null);
    }
}
