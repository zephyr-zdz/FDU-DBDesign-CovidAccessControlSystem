package com.example.accesscontrolsystem.controller.users;

import com.example.accesscontrolsystem.model.entity.user.Student;
import com.example.accesscontrolsystem.service.superUser.UserService;
import com.example.accesscontrolsystem.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("SuperUserStudentController")
@RequestMapping("/student")
public class StudentController {
    private final UserService userservice;
    @Autowired
    public StudentController(UserService userservice) {
        this.userservice = userservice;
    }
    @GetMapping("/all")
    public Response<List<Student>> getStudents() {
        return userservice.getStudents();
    }
    @GetMapping("/part")
    public Response<List<Student>> getPartOfStudents(@RequestParam Integer schoolId, Integer classId) { // TODO
        return userservice.getStudents();
    }
    @PostMapping("/")
    public Response<Student> addStudent(@RequestBody Student student) {
        return userservice.addStudent(student);
    }
}
