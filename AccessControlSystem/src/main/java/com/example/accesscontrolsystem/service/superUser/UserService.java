package com.example.accesscontrolsystem.service.superUser;

import com.example.accesscontrolsystem.manager.StudentManager;
import com.example.accesscontrolsystem.model.entity.Student;
import com.example.accesscontrolsystem.manager.ClassManager;
import com.example.accesscontrolsystem.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("SuperUserUserService")
public class UserService {
    private final StudentManager studentManager;
    private final ClassManager classManager;

    @Autowired
    public UserService(StudentManager studentManager, ClassManager classManager) {
        this.studentManager = studentManager;
        this.classManager = classManager;
    }
    public Response<List<Student>> getStudents() {
        return new Response<>(Response.SUCCESS, "查询成功", studentManager.getStudents());
    }

    public Response<Student> addStudent(Student student) {
        if (studentManager.findStudentById(student.getId()).isPresent()) {
            return new Response<>(Response.FAIL, "学生id已存在", null);
        }
        if (studentManager.findStudentByIdNumberAndIdType(student.getIdNumber(),student.getIdType()).isPresent()) {
            return new Response<>(Response.FAIL, "学生身份证件号码已存在", null);
        }
        if (classManager.findClassById(student.getClassId()).isEmpty()) {
            return new Response<>(Response.FAIL, "学生班级不存在", null);
        }
        return new Response<>(Response.SUCCESS, "添加成功", studentManager.addStudent(student));
    }
}
