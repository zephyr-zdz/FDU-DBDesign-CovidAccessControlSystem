package com.example.accesscontrolsystem.controller.userManagement;

import com.example.accesscontrolsystem.model.entity.user.Counsellor;
import com.example.accesscontrolsystem.service.UserService;
import com.example.accesscontrolsystem.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("SuperUserCounsellorController")
@RequestMapping("/counsellor")
public class CounsellorController {
    private final UserService userservice;
    @Autowired
    public CounsellorController(UserService userservice) {
        this.userservice = userservice;
    }
    @GetMapping("/counsellors")
    public Response<List<Counsellor>> getCounsellors() {
        return userservice.getCounsellors();
    }
    @GetMapping("/{counsellor-id}")
    public Response<Counsellor> getCounsellorById(@PathVariable("counsellor-id") Integer counsellorId) {
        return userservice.getCounsellorById(counsellorId);
    }
}
