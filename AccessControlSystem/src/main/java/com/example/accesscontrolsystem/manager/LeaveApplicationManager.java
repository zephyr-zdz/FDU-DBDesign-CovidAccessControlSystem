package com.example.accesscontrolsystem.manager;

import com.example.accesscontrolsystem.mapper.LeaveApplicationMapper;
import com.example.accesscontrolsystem.model.entity.reportNlog.LeaveApplication;
import com.example.accesscontrolsystem.model.entity.user.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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

    public List<LeaveApplication> findAllByStudents(List<Student> students) {
        List<LeaveApplication> leaveApplications = new ArrayList<>();
        students.stream().map(student -> findAllByStudentId(student.getId())).forEach(leaveApplications::addAll);
        return leaveApplications;
    }

    public List<LeaveApplication> findAll() {
        return leaveApplicationMapper.findAll();
    }

    public List<LeaveApplication> findAllByStatus(String status) {
        return leaveApplicationMapper.findAllByStatus(status);
    }

    public void save(LeaveApplication leaveApplication) {
        leaveApplicationMapper.save(leaveApplication);
    }
}
