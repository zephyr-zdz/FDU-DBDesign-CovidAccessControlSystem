package com.example.accesscontrolsystem.service.student;

import com.example.accesscontrolsystem.manager.StudentManager;
import com.example.accesscontrolsystem.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("StudentDailyReportService")
public class AuthService {
    private final StudentManager studentManager;

    @Autowired
    public AuthService(StudentManager studentManager) {
        this.studentManager = studentManager;
    }
    public Response<String> getAuth(Integer studentId) {
        String auth = studentManager.getAuthById(studentId);
        if (auth == null) {
            return new Response<>(Response.FAIL, "student不存在", null);
        }
        return new Response<>(Response.SUCCESS, "成功", auth);
    }

    public Response<String> getName(Integer studentId) {
        String name = studentManager.getNameById(studentId);
        if (name == null) {
            return new Response<>(Response.FAIL, "student不存在", null);
        }
        return new Response<>(Response.SUCCESS, "成功", name);
    }


}
