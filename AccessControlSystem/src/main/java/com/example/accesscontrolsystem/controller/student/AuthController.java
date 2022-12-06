package com.example.accesscontrolsystem.controller.student;

import com.example.accesscontrolsystem.service.student.AuthService;
import com.example.accesscontrolsystem.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("StudentAuthController")
@RequestMapping("/student/auth")
public class AuthController {
    private final AuthService authService;
    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @GetMapping("/auth")
    public Response<String> getAuth(Integer studentId) {
        return authService.getAuth(studentId);
    }

}
