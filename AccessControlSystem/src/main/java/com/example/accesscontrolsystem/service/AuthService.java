package com.example.accesscontrolsystem.service;

import com.example.accesscontrolsystem.manager.StudentManager;
import com.example.accesscontrolsystem.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("StudentAuthService")
public class AuthService {
    private final StudentManager studentManager;

    @Autowired
    public AuthService(StudentManager studentManager) {
        this.studentManager = studentManager;
    }

    public Response<String> getAuth(Integer classId, Integer schoolId, Integer studentId) {
        if (schoolId == -1) {
            return new Response<>(Response.SUCCESS, "成功", studentManager.findStudentById(studentId).getAuth());
        } else if (classId == -1) {
            return new Response<>(Response.SUCCESS, "成功", studentManager.findStudentByIdAndMajorId(studentId, schoolId).getAuth());
        } else {
            return new Response<>(Response.SUCCESS, "成功", studentManager.findStudentByIdAndMyClassId(studentId, classId).getAuth());
        }
    }
    public Response<String> getName(Integer studentId) {
        String name = studentManager.getNameById(studentId);
        if (name == null) {
            return new Response<>(Response.FAIL, "student不存在", null);
        }
        return new Response<>(Response.SUCCESS, "成功", name);
    }
}
