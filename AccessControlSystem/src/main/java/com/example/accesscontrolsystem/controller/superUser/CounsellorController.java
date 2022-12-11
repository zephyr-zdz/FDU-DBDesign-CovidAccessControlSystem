package com.example.accesscontrolsystem.controller.superUser;

import com.example.accesscontrolsystem.model.entity.Counsellor;
import com.example.accesscontrolsystem.service.superUser.UserService;
import com.example.accesscontrolsystem.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("SuperUserCounsellorController")
@RequestMapping("/super-user/counsellor")
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
    @PostMapping("/")
    public Response<Counsellor> addCounsellor(Counsellor counsellor) {
        return userservice.addCounsellor(counsellor);
    }
}