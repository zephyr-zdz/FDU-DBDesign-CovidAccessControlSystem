package com.example.accesscontrolsystem.controller.counsellor;

import com.example.accesscontrolsystem.model.entity.reportNlog.EnterApplication;
import com.example.accesscontrolsystem.model.entity.reportNlog.LeaveApplication;
import com.example.accesscontrolsystem.service.superUser.ApplicationService;
import com.example.accesscontrolsystem.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("SuperUserApplicationController")
@RequestMapping("/counsellor/")
public class ApplicationController {
    private final ApplicationService applicationService;
    @Autowired
    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("/enter-applications/")
    public Response<List<EnterApplication>> getEnterApplicationsByStudentId(@RequestParam Integer classId,
                                                                            Integer schoolId,
                                                                            Integer studentId,
                                                                            String status) {
        return applicationService.getEnterApplications(classId, schoolId, studentId, status);
    }

    @GetMapping("/leave-applications/")
    public Response<List<LeaveApplication>> getLeaveApplicationsByStudentId(@RequestParam Integer classId,
                                                                            Integer schoolId,
                                                                            Integer studentId,
                                                                            String status) {
        return applicationService.getLeaveApplications(classId, schoolId, studentId, status);
    }
}

