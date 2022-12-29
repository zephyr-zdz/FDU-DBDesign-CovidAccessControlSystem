package com.example.accesscontrolsystem.service;

import com.example.accesscontrolsystem.manager.CounsellorManager;
import com.example.accesscontrolsystem.manager.StudentManager;
import com.example.accesscontrolsystem.model.entity.user.Counsellor;
import com.example.accesscontrolsystem.model.entity.user.Student;
import com.example.accesscontrolsystem.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("SuperUserUserService")
public class UserService {
    private final StudentManager studentManager;
    private final CounsellorManager counsellorManager;

    @Autowired
    public UserService(StudentManager studentManager, CounsellorManager counsellorManager) {
        this.studentManager = studentManager;
        this.counsellorManager = counsellorManager;
    }
    public Response<List<Student>> getStudents() {
        return new Response<>(Response.SUCCESS, "查询成功", studentManager.getStudents());
    }
    public Response<List<Counsellor>> getCounsellors() {
        return new Response<>(Response.SUCCESS, "查询成功", counsellorManager.getCounsellors());
    }
    public Response<Student> getStudentById(Integer studentId) {
        if (studentManager.findStudentById(studentId) == null) {
            return new Response<>(Response.FAIL, "学生不存在", null);
        }
        return new Response<>(Response.SUCCESS, "成功", studentManager.findStudentById(studentId));
    }
}
