package com.example.accesscontrolsystem.controller.common;

import com.example.accesscontrolsystem.model.entity.reportNlog.EnterApplication;
import com.example.accesscontrolsystem.model.entity.reportNlog.LeaveApplication;
import com.example.accesscontrolsystem.service.EnterApplicationService;
import com.example.accesscontrolsystem.service.LeaveApplicationService;
import com.example.accesscontrolsystem.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("SuperUserApplicationController")
@RequestMapping("/application")
public class ApplicationController {
    private final EnterApplicationService enterApplicationService;
    private final LeaveApplicationService leaveApplicationService;
    @Autowired
    public ApplicationController(EnterApplicationService enterApplicationService, LeaveApplicationService leaveApplicationService) {
        this.enterApplicationService = enterApplicationService;
        this.leaveApplicationService = leaveApplicationService;
    }

    @GetMapping("/enter-applications/")
    public Response<List<EnterApplication>> getEnterApplicationsByStudentId(Integer classId,
                                                                            Integer schoolId,
                                                                            Integer studentId,
                                                                            String status) {
        return enterApplicationService.getEnterApplications(classId, schoolId, studentId, status);
    }
    @GetMapping("/enter-applications/pending/counsellor")
    public Response<List<EnterApplication>> getPendingEnterApplicationsByCounsellorId(@RequestParam("counsellorId") Integer counsellorId, @RequestParam("n") Integer n) {
        return enterApplicationService.getLastNDaysPendingApplicationByCounsellorId(counsellorId, n);
    }
    @GetMapping("/enter-applications/pending/manager")
    public Response<List<EnterApplication>> getPendingEnterApplicationsByManager(@RequestParam("managerId") Integer managerId, @RequestParam("n") Integer n) {
        return enterApplicationService.getLastNDaysPendingApplicationByManagerId(managerId, n);
    }

    @GetMapping("/leave-applications/")
    public Response<List<LeaveApplication>> getLeaveApplicationsByStudentId(Integer classId,
                                                                            Integer schoolId,
                                                                            Integer studentId,
                                                                            String status) {
        return leaveApplicationService.getLeaveApplications(classId, schoolId, studentId, status);
    }

    @GetMapping("/leave-applications/pending/counsellor")
    public Response<List<LeaveApplication>> getPendingLeaveApplicationsByCounsellorId(@RequestParam("counsellorId") Integer counsellorId, @RequestParam("n") Integer n) {
        return leaveApplicationService.getLastNDaysPendingApplicationByCounsellorId(counsellorId, n);
    }

    @GetMapping("/leave-applications/pending/manager")
    public Response<List<LeaveApplication>> getPendingLeaveApplicationsByManager(@RequestParam("managerId") Integer managerId, @RequestParam("n") Integer n) {
        return leaveApplicationService.getLastNDaysPendingApplicationByManagerId(managerId, n);
    }
}
