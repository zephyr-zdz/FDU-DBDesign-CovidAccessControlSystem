package com.example.accesscontrolsystem.service.student;

import com.example.accesscontrolsystem.manager.EnterApplicationManager;
import com.example.accesscontrolsystem.manager.LeaveApplicationManager;
import com.example.accesscontrolsystem.manager.StudentManager;
import com.example.accesscontrolsystem.model.entity.EnterApplication;
import com.example.accesscontrolsystem.model.entity.LeaveApplication;
import com.example.accesscontrolsystem.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("StudentApplicationService")
public class ApplicationService {
    private final EnterApplicationManager enterApplicationManager;
    private final LeaveApplicationManager leaveApplicationManager;
    private final StudentManager studentManager;

    @Autowired
    public ApplicationService(EnterApplicationManager enterApplicationManager, LeaveApplicationManager leaveApplicationManager, StudentManager studentManager) {
        this.enterApplicationManager = enterApplicationManager;
        this.leaveApplicationManager = leaveApplicationManager;
        this.studentManager = studentManager;
    }

    public Response<List<EnterApplication>> getEnterApplicationsByStudentId(Integer studentId) {
        if (studentManager.findStudentById(studentId).isEmpty()) {
            return new Response<>(Response.FAIL, "studentId为空", null);
        }
        return new Response<>(Response.SUCCESS, "成功", enterApplicationManager.findAllByStudentId(studentId));
    }

    public Response<List<LeaveApplication>> getLeaveApplicationsByStudentId(Integer studentId) {
        if (studentManager.findStudentById(studentId).isEmpty()) {
            return new Response<>(Response.FAIL, "studentId为空", null);
        }
        return new Response<>(Response.SUCCESS, "成功", leaveApplicationManager.findAllByStudentId(studentId));
    }
}
