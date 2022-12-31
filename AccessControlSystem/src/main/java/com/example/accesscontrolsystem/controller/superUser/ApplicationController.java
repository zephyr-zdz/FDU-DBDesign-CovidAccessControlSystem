package com.example.accesscontrolsystem.controller.superUser;

import com.example.accesscontrolsystem.service.EnterApplicationService;
import com.example.accesscontrolsystem.service.LeaveApplicationService;
import com.example.accesscontrolsystem.util.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("ApplicationApprovalController")
public class ApplicationController {
    private final EnterApplicationService enterApplicationService;
    private final LeaveApplicationService leaveApplicationService;

    public ApplicationController(EnterApplicationService enterApplicationService, LeaveApplicationService leaveApplicationService) {
        this.enterApplicationService = enterApplicationService;
        this.leaveApplicationService = leaveApplicationService;
    }
    @PostMapping("/enter-application/counsellor/approve")
    public Response<String> approveEnterApplicationByCounsellor(Integer applicationId) {
        return enterApplicationService.approveEnterApplicationByCounsellor(applicationId);
    }
    @PostMapping("/enter-application/counsellor/reject")
    public Response<String> rejectEnterApplicationByCounsellor(Integer applicationId, String reason) {
        return enterApplicationService.rejectEnterApplicationByCounsellor(applicationId, reason);
    }
    @PostMapping("/enter-application/manager/approve")
    public Response<String> approveEnterApplicationByManager(Integer applicationId) {
        return enterApplicationService.approveEnterApplicationByManager(applicationId);
    }
    @PostMapping("/enter-application/manager/reject")
    public Response<String> rejectEnterApplicationByManager(Integer applicationId, String reason) {
        return enterApplicationService.rejectEnterApplicationByManager(applicationId, reason);
    }
    @PostMapping("/leave-application/counsellor/approve")
    public Response<String> approveLeaveApplicationByCounsellor(Integer applicationId) {
        return leaveApplicationService.approveLeaveApplicationByCounsellor(applicationId);
    }
    @PostMapping("/leave-application/counsellor/reject")
    public Response<String> rejectLeaveApplicationByCounsellor(Integer applicationId, String reason) {
        return leaveApplicationService.rejectLeaveApplicationByCounsellor(applicationId, reason);
    }
    @PostMapping("/leave-application/manager/approve")
    public Response<String> approveLeaveApplicationByManager(Integer applicationId) {
        return leaveApplicationService.approveLeaveApplicationByManager(applicationId);
    }
    @PostMapping("/leave-application/manager/reject")
    public Response<String> rejectLeaveApplicationByManager(Integer applicationId, String reason) {
        return leaveApplicationService.rejectLeaveApplicationByManager(applicationId, reason);
    }
}
