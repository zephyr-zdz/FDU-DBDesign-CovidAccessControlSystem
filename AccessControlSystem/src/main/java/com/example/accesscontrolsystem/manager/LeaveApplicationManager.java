package com.example.accesscontrolsystem.manager;

import com.example.accesscontrolsystem.mapper.LeaveApplicationMapper;
import com.example.accesscontrolsystem.model.entity.LeaveApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("LeaveApplicationManager")
public class LeaveApplicationManager {
    private final LeaveApplicationMapper leaveApplicationMapper;
    @Autowired
    public LeaveApplicationManager(LeaveApplicationMapper leaveApplicationMapper) {
        this.leaveApplicationMapper = leaveApplicationMapper;
    }

    public List<LeaveApplication> findAllByStudentId(Integer studentId) {
        return leaveApplicationMapper.findAllByStudentId(studentId);
    }

    public List<LeaveApplication> findAllByStudentIdAndStatus(Integer studentId, String status) {
        return leaveApplicationMapper.findAllByStudentIdAndStatus(studentId, status);
    }
}
