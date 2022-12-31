package com.example.accesscontrolsystem.service;

import com.example.accesscontrolsystem.manager.*;
import com.example.accesscontrolsystem.mapper.CounsellorMapper;
import com.example.accesscontrolsystem.model.ClassAdapter;
import com.example.accesscontrolsystem.model.entity.Class;
import com.example.accesscontrolsystem.model.entity.Major;
import com.example.accesscontrolsystem.model.entity.reportNlog.LeaveApplication;
import com.example.accesscontrolsystem.model.entity.user.Counsellor;
import com.example.accesscontrolsystem.model.entity.user.SchoolManager;
import com.example.accesscontrolsystem.model.entity.user.Student;
import com.example.accesscontrolsystem.model.vo.RawLeaveApplication;
import com.example.accesscontrolsystem.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service("StudentLeaveApplicationService")
public class LeaveApplicationService {
    private final LeaveApplicationManager leaveApplicationManager;
    private final StudentManager studentManager;
    private final SchoolManagerManager schoolManagerManager;
    private final ClassManager classManager;
    private final MajorManager majorManager;
    private final ClassAdapter classAdapter;
    private final CounsellorMapper counsellorMapper;


    @Autowired
    public LeaveApplicationService(LeaveApplicationManager leaveApplicationManager,
                                   StudentManager studentManager, SchoolManagerManager schoolManagerManager, ClassManager classManager,
                                   MajorManager majorManager, ClassAdapter classAdapter, CounsellorMapper counsellorMapper) {
        this.leaveApplicationManager = leaveApplicationManager;
        this.studentManager = studentManager;
        this.schoolManagerManager = schoolManagerManager;
        this.classManager = classManager;
        this.majorManager = majorManager;
        this.classAdapter = classAdapter;
        this.counsellorMapper = counsellorMapper;
    }
    private Response<List<LeaveApplication>> getMyStudentLeaveApplications(Integer studentId, List<Student> students) {
        if (studentId == -1) { // all
            return new Response<>(Response.SUCCESS,"获取成功",leaveApplicationManager.findAllByStudents(students));
        }
        Student student = studentManager.findStudentById(studentId); // single
        if (students.contains(student)) {
            return new Response<>(Response.SUCCESS, "获取成功", leaveApplicationManager.findAllByStudentId(studentId));
        } else {
            return new Response<>(Response.FAIL,"你没有权限",null);
        }
    }
    public Response<List<LeaveApplication>> getLeaveApplications(Integer classId, Integer schoolId, Integer studentId, String status) {
        if (classId == -1 && schoolId == -1) { // super admin
            if (studentId == -1 && Objects.equals(status, "")) {
                return new Response<>(Response.SUCCESS, "获取成功", leaveApplicationManager.findAll());
            } else if (Objects.equals(status, "")) {
                return new Response<>(Response.SUCCESS, "获取成功", leaveApplicationManager.findAllByStudentId(studentId));
            } else if (studentId == -1) {
                return new Response<>(Response.SUCCESS, "获取成功", leaveApplicationManager.findAllByStatus(status));
            } else {
                return new Response<>(Response.SUCCESS, "获取成功", leaveApplicationManager.findAllByStudentIdAndStatus(studentId, status));
            }
        } else if (classId == -1) { // school admin
            Major major = majorManager.findMajorById(schoolId);
            List<Student> students = studentManager.findAllByMajorId(major.getId());
            return getMyStudentLeaveApplications(studentId, students);
        } else if (classId > 0) { // counselor
            Class clazz = classManager.findClassById(classId);
            List<Student> students = studentManager.findAllByClassId(clazz.getId());
            return getMyStudentLeaveApplications(studentId, students);
        } else {
            return new Response<>(Response.FAIL, "获取失败", null);
        }
    }
    public Response<List<LeaveApplication>> getLastNDaysPendingApplicationByCounsellorId(Integer counsellorId, Integer n) {
        if (counsellorId == -1) {
            return new Response<>(Response.SUCCESS, "获取成功", leaveApplicationManager.findLastNDaysPendingApplication(n));
        }
        Counsellor counsellor = counsellorMapper.findCounsellorById(counsellorId);
        if (counsellor == null) {
            return new Response<>(Response.FAIL, "辅导员不存在", null);
        }
        return new Response<>(Response.SUCCESS, "获取成功", leaveApplicationManager.findLastNDaysByCounsellorAndStatus(counsellor, n));
    }
    public Response<List<LeaveApplication>> getLastNDaysPendingApplicationByManagerId(Integer managerId, Integer n) {
        if (managerId == -1) {
            return new Response<>(Response.SUCCESS, "获取成功", leaveApplicationManager.findLastNDaysPendingApplication(n));
        }
        SchoolManager schoolManager = schoolManagerManager.findSchoolManagerById(managerId);
        if (schoolManager == null) {
            return new Response<>(Response.FAIL, "学校管理员不存在", null);
        }
        return new Response<>(Response.SUCCESS, "获取成功", leaveApplicationManager.findLastNDaysBySchoolManagerAndStatus(schoolManager, n));
    }
    public Response<LeaveApplication> addLeaveApplicationsByStudentId(RawLeaveApplication leaveApplication) {
        Student student = studentManager.findStudentById(leaveApplication.getStudentId());
        if (student == null) {
            return new Response<>(Response.FAIL, "学生不存在", null);
        }
        if (Objects.equals(student.getStatus(), "outside")) {
            return new Response<>(Response.FAIL, "学生已离校", null);
        }
        leaveApplicationManager.save(classAdapter.cookLeaveApplication(leaveApplication));
        return new Response<>(Response.SUCCESS, "添加成功", null);
    }

    public Response<String> approveLeaveApplicationByCounsellor(Integer applicationId) {
        LeaveApplication leaveApplication = leaveApplicationManager.findLeaveApplicationById(applicationId);
        if (leaveApplication == null) {
            return new Response<>(Response.FAIL, "申请不存在", null);
        }
        leaveApplication.setStatus("counsellor");
        leaveApplicationManager.save(leaveApplication);
        return new Response<>(Response.SUCCESS, "审批成功", null);
    }

    public Response<String> rejectLeaveApplicationByCounsellor(Integer applicationId, String reason) {
        LeaveApplication leaveApplication = leaveApplicationManager.findLeaveApplicationById(applicationId);
        if (leaveApplication == null) {
            return new Response<>(Response.FAIL, "申请不存在", null);
        }
        leaveApplication.setStatus("rejected");
        leaveApplication.setRejectReason(reason);
        leaveApplicationManager.save(leaveApplication);
        return new Response<>(Response.SUCCESS, "审批成功", null);
    }

    public Response<String> approveLeaveApplicationByManager(Integer applicationId) {
        LeaveApplication leaveApplication = leaveApplicationManager.findLeaveApplicationById(applicationId);
        if (leaveApplication == null) {
            return new Response<>(Response.FAIL, "申请不存在", null);
        }
        leaveApplication.setStatus("accepted");
        leaveApplicationManager.save(leaveApplication);
        return new Response<>(Response.SUCCESS, "审批成功", null);
    }

    public Response<String> rejectLeaveApplicationByManager(Integer applicationId, String reason) {
        LeaveApplication leaveApplication = leaveApplicationManager.findLeaveApplicationById(applicationId);
        if (leaveApplication == null) {
            return new Response<>(Response.FAIL, "申请不存在", null);
        }
        leaveApplication.setStatus("rejected");
        leaveApplication.setRejectReason(reason);
        leaveApplicationManager.save(leaveApplication);
        return new Response<>(Response.SUCCESS, "审批成功", null);
    }
}
