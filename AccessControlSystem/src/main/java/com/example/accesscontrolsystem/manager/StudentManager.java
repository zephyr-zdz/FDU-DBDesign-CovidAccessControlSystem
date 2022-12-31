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

    public String getNameById(Integer studentId) {
        Student student = studentMapper.findStudentById(studentId);
        return student.getName();
    }

    public List<Student> getStudents() {
        return studentMapper.findAll();
    }

    public void update(Student student) {
        studentMapper.save(student);
    }

    public List<Student> findAllByMajorId(Integer id) {
        List<Student> students = new ArrayList<>();
        classManager.findClassesByMajorId(id).forEach(aClass -> students.addAll(studentMapper.findStudentsByMyClass_Id(aClass.getId())));
        return students;
    }
    public List<Student> findAllByClassId(Integer id) {
        return studentMapper.findStudentsByMyClass_Id(id);
    }

    public Student findStudentByIdAndMajorId(Integer studentId, Integer majorId) {
        return studentMapper.findStudentByIdAndMyClass_Major_Id(studentId, majorId);
    }

    public Student findStudentByIdAndMyClassId(Integer studentId, Integer classId) {
        return studentMapper.findStudentByIdAndMyClass_Id(studentId, classId);
    }

    public Student findStudentByIdAndSchoolId(Integer studentId, Integer schoolId) {
        return studentMapper.findStudentByIdAndMyClass_Major_Id(studentId, schoolId);
    }

    public Student findStudentByIdAndClassId(Integer studentId, Integer classId) {
        return studentMapper.findStudentByIdAndMyClass_Id(studentId, classId);
    }
}
