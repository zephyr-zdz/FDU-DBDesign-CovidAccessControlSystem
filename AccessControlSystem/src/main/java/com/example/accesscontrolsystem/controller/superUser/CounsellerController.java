package com.example.accesscontrolsystem.controller.superUser;

import com.example.accesscontrolsystem.model.entity.Student;
import com.example.accesscontrolsystem.service.superUser.UserService;
import com.example.accesscontrolsystem.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("SuperUserStudentController")
@RequestMapping("/super-user/student")
public class CounsellerController {
    private final UserService userservice;
    @Autowired
    public CounsellerController(UserService userservice) {
        this.userservice = userservice;
    }
    @GetMapping("/students")
    public Response<List<Student>> getStudents() {
        return userservice.getStudents();
    }
}
