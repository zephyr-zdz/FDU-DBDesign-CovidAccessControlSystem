package com.example.accesscontrolsystem.controller.student;

import com.example.accesscontrolsystem.model.entity.EnterApplication;
import com.example.accesscontrolsystem.model.entity.LeaveApplication;
import com.example.accesscontrolsystem.service.student.ApplicationService;
import com.example.accesscontrolsystem.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("StudentApplicationController")
public class ApplicationController {
    private final ApplicationService applicationService;
    @Autowired
    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("/enter-applications")
    public Response<List<EnterApplication>> getEnterApplicationsByStudentId(Integer studentId) {
        return applicationService.getEnterApplicationsByStudentId(studentId);
    }

    @GetMapping("/leave-applications")
    public Response<List<LeaveApplication>> getLeaveApplicationsByStudentId(Integer studentId) {
        return applicationService.getLeaveApplicationsByStudentId(studentId);
    }
    @GetMapping("/enter-applications/{status}")
    public Response<List<EnterApplication>> getEnterApplicationsByStudentIdAndStatus(Integer studentId, @PathVariable("status") String status) {
        return applicationService.getEnterApplicationsByStudentIdAndStatus(studentId, status);
    }

    @GetMapping("/leave-applications/{status}")
    public Response<List<LeaveApplication>> getLeaveApplicationsByStudentIdAndStatus(Integer studentId, @PathVariable("status") String status) {
        return applicationService.getLeaveApplicationsByStudentIdAndStatus(studentId, status);
    }
}
