package com.example.accesscontrolsystem.service.superUser;

import com.example.accesscontrolsystem.manager.StudentManager;
import com.example.accesscontrolsystem.model.entity.Student;
import com.example.accesscontrolsystem.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("SuperUserUserService")
public class UserService {
    private final StudentManager studentManager;

    @Autowired
    public UserService(StudentManager studentManager) {
        this.studentManager = studentManager;
    }
    public Response<List<Student>> getStudents() {
        return new Response<>(Response.SUCCESS, "查询成功", studentManager.getStudents());
    }
}
