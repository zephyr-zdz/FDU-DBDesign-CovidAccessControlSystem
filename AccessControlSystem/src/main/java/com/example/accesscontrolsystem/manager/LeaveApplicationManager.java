package com.example.accesscontrolsystem.manager;

import com.example.accesscontrolsystem.mapper.LeaveApplicationMapper;
import com.example.accesscontrolsystem.mapper.StudentMapper;
import com.example.accesscontrolsystem.model.entity.reportNlog.LeaveApplication;
import com.example.accesscontrolsystem.model.entity.user.Counsellor;
import com.example.accesscontrolsystem.model.entity.user.SchoolManager;
import com.example.accesscontrolsystem.model.entity.user.Student;
import com.example.accesscontrolsystem.service.system.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("LeaveApplicationManager")
public class LeaveApplicationManager {
    private final LeaveApplicationMapper leaveApplicationMapper;
    private final TimeService timeService;
    private final StudentMapper studentMapper;

    @Autowired
    public LeaveApplicationManager(LeaveApplicationMapper leaveApplicationMapper, TimeService timeService,
                                   StudentMapper studentMapper) {
        this.leaveApplicationMapper = leaveApplicationMapper;
        this.timeService = timeService;
        this.studentMapper = studentMapper;
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

    public List<LeaveApplication> findLastNDaysByCounsellorAndStatus(Counsellor counsellor, Integer n) {
        long today = timeService.getTime();
        long nDaysBefore = today - (long) n * 24 * 60 * 60 * 1000;
        return leaveApplicationMapper.findAllByCounsellorAndStatusAndCreateTimeBetween(counsellor, "pending", today, nDaysBefore);
    }

    public List<LeaveApplication> findLastNDaysBySchoolManagerAndStatus(SchoolManager schoolManager, Integer n) {
        long today = timeService.getTime();
        long nDaysBefore = today - (long) n * 24 * 60 * 60 * 1000;
        return leaveApplicationMapper.findAllByManagerAndStatusAndCreateTimeBetween(schoolManager, "counsellor", today, nDaysBefore);
    }

    public List<Student> findAppliedButNotLeaved() {
        return studentMapper.findAppliedButNotLeaved(timeService.getTime());
    }

    public List<Student> findAppliedButNotLeavedBySchoolId(Integer schoolId) {
        return studentMapper.findAppliedButNotLeavedBySchoolId(timeService.getTime(), schoolId);
    }

    public List<Student> findAppliedButNotLeavedByClassId(Integer classId) {
        return studentMapper.findAppliedButNotLeavedByClassId(timeService.getTime(), classId);
    }
}
