package com.example.accesscontrolsystem.controller.student;

import com.example.accesscontrolsystem.model.entity.reportNlog.EnterApplication;
import com.example.accesscontrolsystem.model.entity.reportNlog.LeaveApplication;
import com.example.accesscontrolsystem.model.vo.RawEnterApplication;
import com.example.accesscontrolsystem.model.vo.RawLeaveApplication;
import com.example.accesscontrolsystem.service.ApplicationService;
import com.example.accesscontrolsystem.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("StudentApplicationController")
@RequestMapping("/student/application")
public class StudentApplicationController {
    private final ApplicationService applicationService;
    @Autowired
    public StudentApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping("/enter-applications")
    public Response<EnterApplication> addEnterApplicationsByStudentId(@RequestBody RawEnterApplication enterApplication) {
        return applicationService.addEnterApplicationsByStudentId(enterApplication);
    }

    @PostMapping("/leave-applications")
    public Response<LeaveApplication> addLeaveApplicationsByStudentId(@RequestBody RawLeaveApplication leaveApplication) {
        return applicationService.addLeaveApplicationsByStudentId(leaveApplication);
    }
}
