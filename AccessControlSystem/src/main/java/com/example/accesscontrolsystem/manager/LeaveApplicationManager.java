package com.example.accesscontrolsystem.manager;

import com.example.accesscontrolsystem.mapper.GateLogMapper;
import com.example.accesscontrolsystem.mapper.LeaveApplicationMapper;
import com.example.accesscontrolsystem.mapper.StudentMapper;
import com.example.accesscontrolsystem.model.ClassAdapter;
import com.example.accesscontrolsystem.model.entity.reportNlog.LeaveApplication;
import com.example.accesscontrolsystem.model.entity.user.Student;
import com.example.accesscontrolsystem.model.vo.StudentWithLeaveTime;
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
    private final ClassAdapter classAdapter;
    private final GateLogMapper gateLogMapper;

    @Autowired
    public LeaveApplicationManager(LeaveApplicationMapper leaveApplicationMapper, TimeService timeService,
                                   StudentMapper studentMapper, ClassAdapter classAdapter,
                                   GateLogMapper gateLogMapper) {
        this.leaveApplicationMapper = leaveApplicationMapper;
        this.timeService = timeService;
        this.studentMapper = studentMapper;
        this.classAdapter = classAdapter;
        this.gateLogMapper = gateLogMapper;
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

    public List<LeaveApplication> findLastNDaysByCounsellorIdAndStatus(Integer counsellorId, Integer n) {
        long today = timeService.getTime();
        long nDaysBefore = timeService.getTimeNDaysBefore(n);
        System.out.println("today: " + today);
        System.out.println("nDaysBefore: " + nDaysBefore);
        System.out.println(counsellorId);
        return leaveApplicationMapper.findAllByCounsellorIdAndStatusAndCreateTimeBetween(counsellorId, "pending", nDaysBefore, today);
    }

    public List<LeaveApplication> findLastNDaysBySchoolManagerIdAndStatus(Integer schoolManagerId, Integer n) {
        long today = timeService.getTime();
        long nDaysBefore = today - (long) n * 24 * 60 * 60 * 1000;
        return leaveApplicationMapper.findAllByManager_IdAndStatusAndCreateTimeBetween(schoolManagerId, "counsellor", nDaysBefore, today);
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

    public List<Student> findLeaved24hrsButNotApplied() {
        return studentMapper.findLeaved24hrsButNotApplied(timeService.getTime());
    }

    public List<Student> findLeaved24hrsButNotAppliedBySchoolId(Integer schoolId) {
        return studentMapper.findLeaved24hrsButNotAppliedBySchoolId(timeService.getTime(), schoolId);
    }

    public List<Student> findLeaved24hrsButNotAppliedByClassId(Integer classId) {
        return studentMapper.findLeaved24hrsButNotAppliedByClassId(timeService.getTime(), classId);
    }

    public List<StudentWithLeaveTime> findOutsideStudents() {
        List<Student> students = studentMapper.findStudentsByStatus("out");
        List<StudentWithLeaveTime> studentsWithLeaveTime = new ArrayList<>();
        students.forEach(student -> studentsWithLeaveTime.add(classAdapter.cookStudentWithLeaveTime(student,
                gateLogMapper.findGateLogByStudentIdAndDirectionIsOutOrderByTimeDesc(student.getId(), "out") == null ?
                        -1 : gateLogMapper.findGateLogByStudentIdAndDirectionIsOutOrderByTimeDesc(student.getId(), "out").getTime())));
        return studentsWithLeaveTime;
    }

    public List<StudentWithLeaveTime> findOutsideStudentsBySchoolId(Integer schoolId) {
        List<Student> students = studentMapper.findStudentsByStatusAndMajorId("out", schoolId);
        List<StudentWithLeaveTime> studentsWithLeaveTime = new ArrayList<>();
        students.forEach(student -> studentsWithLeaveTime.add(classAdapter.cookStudentWithLeaveTime(student,
                gateLogMapper.findGateLogByStudentIdAndDirectionIsOutOrderByTimeDesc(student.getId(), "out") == null ?
                        -1 : gateLogMapper.findGateLogByStudentIdAndDirectionIsOutOrderByTimeDesc(student.getId(), "out").getTime())));
        return studentsWithLeaveTime;
    }

    public List<StudentWithLeaveTime> findOutsideStudentsByClassId(Integer classId) {
        List<Student> students = studentMapper.findStudentsByStatusAndMyClassId("out", classId);
        List<StudentWithLeaveTime> studentsWithLeaveTime = new ArrayList<>();
        students.forEach(student -> studentsWithLeaveTime.add(classAdapter.cookStudentWithLeaveTime(student,
                gateLogMapper.findGateLogByStudentIdAndDirectionIsOutOrderByTimeDesc(student.getId(), "out") == null ?
                        -1 : gateLogMapper.findGateLogByStudentIdAndDirectionIsOutOrderByTimeDesc(student.getId(), "out").getTime())));
        return studentsWithLeaveTime;
    }

    public List<Student> findNStudentsWithMostLeaveTime(Integer n) {
        return studentMapper.findNStudentsWithMostLeaveTime(n);
    }

    public List<Student> findNStudentsWithMostLeaveTimeBySchoolId(Integer n, Integer schoolId) {
        return studentMapper.findNStudentsWithMostLeaveTimeByMajorId(n, schoolId);
    }

    public List<Student> findNStudentsWithMostLeaveTimeByClassId(Integer n, Integer classId) {
        return studentMapper.findNStudentsWithMostLeaveTimeByClassId(n, classId);
    }

    public List<Student> findNStudentsWithLongestAvgOutsideTime(Integer n) {
        return studentMapper.findNStudentsWithLongestAvgOutsideTime(n);
    }

    public List<Student> findNStudentsWithLongestAvgOutsideTimeBySchoolId(Integer n, Integer schoolId) {
        return studentMapper.findNStudentsWithLongestAvgOutsideTimeByMajorId(n, schoolId);
    }

    public List<Student> findNStudentsWithLongestAvgOutsideTimeByClassId(Integer n, Integer classId) {
        return studentMapper.findNStudentsWithLongestAvgOutsideTimeByClassId(n, classId);
    }

    public LeaveApplication findLeaveApplicationById(Integer applicationId) {
        return leaveApplicationMapper.findLeaveApplicationById(applicationId);
    }

    public List<LeaveApplication> findLastNDaysPendingApplication(Integer n, String status) {
        long today = timeService.getTime();
        long nDaysBefore = timeService.getTimeNDaysBefore(n);
        return leaveApplicationMapper.findAllByStatusAndCreateTimeBetween(status, today, nDaysBefore);
    }

}
