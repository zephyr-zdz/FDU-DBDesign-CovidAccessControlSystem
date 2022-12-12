package com.example.accesscontrolsystem.manager;

import com.example.accesscontrolsystem.mapper.StudentMapper;
import com.example.accesscontrolsystem.model.entity.user.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("StudentManager")
public class StudentManager {
    private final StudentMapper studentMapper;
    private final ClassManager classManager;

    @Autowired
    public StudentManager(StudentMapper studentMapper,
                          ClassManager classManager) {
        this.studentMapper = studentMapper;
        this.classManager = classManager;
    }

    public Student findStudentById(Integer studentId) {
        return studentMapper.findStudentById(studentId);
    }

    public String getAuthById(Integer studentId) {
        Student student = studentMapper.findStudentById(studentId);
        return student.getAuth();
    }

    public String getNameById(Integer studentId) {
        Student student = studentMapper.findStudentById(studentId);
        return student.getName();
    }

    public List<Student> getStudents() {
        return studentMapper.findAll();
    }

    public Student addStudent(Student student) {
        return studentMapper.save(student);
    }

    public Student findStudentByEmail(String email) {
        return studentMapper.findStudentByEmail(email);
    }

    public Student findStudentByIdNumberAndIdType(String idNumber, String idType) {
        return studentMapper.findStudentByIdNumberAndIdType(idNumber, idType);
    }

    public void update(Student student) {
        studentMapper.save(student);
    }

    public List<Student> findAllByMajorId(Integer id) {
        List<Student> students = new ArrayList<>();
        classManager.findClassesByMajorId(id).forEach(aClass -> students.addAll(studentMapper.findStudentsByClassId(aClass.getId())));
        return students;
    }
    public List<Student> findAllByClassId(Integer id) {
        return studentMapper.findStudentsByClassId(id);
    }
}
