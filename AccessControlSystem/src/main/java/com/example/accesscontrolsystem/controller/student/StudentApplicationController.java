package com.example.accesscontrolsystem.controller.student;

import com.example.accesscontrolsystem.model.entity.reportNlog.EnterApplication;
import com.example.accesscontrolsystem.model.entity.reportNlog.LeaveApplication;
import com.example.accesscontrolsystem.model.vo.RawEnterApplication;
import com.example.accesscontrolsystem.model.vo.RawLeaveApplication;
import com.example.accesscontrolsystem.service.EnterApplicationService;
import com.example.accesscontrolsystem.service.LeaveApplicationService;
import com.example.accesscontrolsystem.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("StudentApplicationController")
@RequestMapping("/student/application")
public class StudentApplicationController {
    private final EnterApplicationService enterApplicationService;
    private final LeaveApplicationService leaveApplicationService;
    @Autowired
    public StudentApplicationController(EnterApplicationService enterApplicationService, LeaveApplicationService leaveApplicationService) {
        this.enterApplicationService = enterApplicationService;
        this.leaveApplicationService = leaveApplicationService;
    }

    @PostMapping("/enter-applications")
    public Response<EnterApplication> addEnterApplicationsByStudentId(@RequestBody RawEnterApplication enterApplication) {
        return enterApplicationService.addEnterApplicationsByStudentId(enterApplication);
    }

    @PostMapping("/leave-applications")
    public Response<LeaveApplication> addLeaveApplicationsByStudentId(@RequestBody RawLeaveApplication leaveApplication) {
        return leaveApplicationService.addLeaveApplicationsByStudentId(leaveApplication);
    }
}
