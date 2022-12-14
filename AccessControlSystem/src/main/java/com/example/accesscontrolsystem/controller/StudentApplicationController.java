package com.example.accesscontrolsystem.controller;

import com.example.accesscontrolsystem.model.entity.reportNlog.EnterApplication;
import com.example.accesscontrolsystem.model.entity.reportNlog.LeaveApplication;
import com.example.accesscontrolsystem.service.superUser.ApplicationService;
import com.example.accesscontrolsystem.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("ApplicationController")
@RequestMapping("/application")
public class StudentApplicationController {
    private final ApplicationService applicationService;
    @Autowired
    public StudentApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping("/enter-applications")
    public Response<EnterApplication> addEnterApplicationsByStudentId(@RequestBody EnterApplication enterApplication) {
        return applicationService.addEnterApplicationsByStudentId(enterApplication);
    }

    @PostMapping("/leave-applications")
    public Response<LeaveApplication> addLeaveApplicationsByStudentId(@RequestBody LeaveApplication leaveApplication) {
        return applicationService.addLeaveApplicationsByStudentId(leaveApplication);
    }
}
