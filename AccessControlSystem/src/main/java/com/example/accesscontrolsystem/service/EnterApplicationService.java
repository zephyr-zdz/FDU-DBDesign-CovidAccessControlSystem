package com.example.accesscontrolsystem.service;

import com.example.accesscontrolsystem.manager.*;
import com.example.accesscontrolsystem.mapper.CounsellorMapper;
import com.example.accesscontrolsystem.model.ClassAdapter;
import com.example.accesscontrolsystem.model.entity.*;
import com.example.accesscontrolsystem.model.entity.Class;
import com.example.accesscontrolsystem.model.entity.reportNlog.EnterApplication;
import com.example.accesscontrolsystem.model.entity.user.Counsellor;
import com.example.accesscontrolsystem.model.entity.user.SchoolManager;
import com.example.accesscontrolsystem.model.entity.user.Student;
import com.example.accesscontrolsystem.model.vo.RawEnterApplication;
import com.example.accesscontrolsystem.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service("StudentEnterApplicationService")
public class EnterApplicationService {
    private final EnterApplicationManager enterApplicationManager;
    private final StudentManager studentManager;
    private final ClassManager classManager;
    private final MajorManager majorManager;
    private final ClassAdapter classAdapter;
    private final CounsellorMapper counsellorMapper;
    private final SchoolManagerManager schoolManagerManager;

    @Autowired
    public EnterApplicationService(EnterApplicationManager enterApplicationManager,
                                   StudentManager studentManager, ClassManager classManager,
                                   MajorManager majorManager, ClassAdapter classAdapter,
                                   CounsellorMapper counsellorMapper, SchoolManagerManager schoolManagerManager) {
        this.enterApplicationManager = enterApplicationManager;
        this.studentManager = studentManager;
        this.schoolManagerManager = schoolManagerManager;
        this.classManager = classManager;
        this.majorManager = majorManager;
        this.classAdapter = classAdapter;
        this.counsellorMapper = counsellorMapper;
    }
    private Response<List<EnterApplication>> getMyStudentEnterApplications(Integer studentId, List<Student> students) {
        if (studentId == -1) { // all
            return new Response<>(Response.SUCCESS,"获取成功",enterApplicationManager.findAllByStudents(students));
        }
        Student student = studentManager.findStudentById(studentId); // single
        if (students.contains(student)) {
            return new Response<>(Response.SUCCESS, "获取成功", enterApplicationManager.findAllByStudentId(studentId));
        } else {
            return new Response<>(Response.FAIL,"你没有权限",null);
        }
    }
    public Response<List<EnterApplication>> getEnterApplications(Integer classId, Integer schoolId, Integer studentId, String status) {
        if (classId == -1 && schoolId == -1) { // super admin
            if (studentId == -1 && Objects.equals(status, "")) {
                return new Response<>(Response.SUCCESS, "获取成功", enterApplicationManager.findAll());
            } else if (Objects.equals(status, "")) {
                return new Response<>(Response.SUCCESS, "获取成功", enterApplicationManager.findAllByStudentId(studentId));
            } else if (studentId == -1) {
                return new Response<>(Response.SUCCESS, "获取成功", enterApplicationManager.findAllByStatus(status));
            } else {
                return new Response<>(Response.SUCCESS, "获取成功", enterApplicationManager.findAllByStudentIdAndStatus(studentId, status));
            }
        } else if (classId == -1) { // school admin
            Major major = majorManager.findMajorById(schoolId);
            List<Student> students = studentManager.findAllByMajorId(major.getId());
            return getMyStudentEnterApplications(studentId, students);
        } else if (classId > 0) { // counsellor
            Class clazz = classManager.findClassById(classId);
            List<Student> students = studentManager.findAllByClassId(clazz.getId());
            return getMyStudentEnterApplications(studentId, students);
        } else {
            return new Response<>(Response.FAIL, "获取失败", null);
        }
    }

    public Response<List<EnterApplication>> getLastNDaysPendingApplicationByCounsellorId(Integer counsellorId, Integer n) {
        Counsellor counsellor = counsellorMapper.findCounsellorById(counsellorId);
        if (counsellor == null) {
            return new Response<>(Response.FAIL, "辅导员不存在", null);
        }
        return new Response<>(Response.SUCCESS, "获取成功", enterApplicationManager.findLastNDaysByCounsellorAndStatus(counsellor, n));
    }
    public Response<List<EnterApplication>> getLastNDaysPendingApplicationByManagerId(Integer managerId, Integer n) {
        SchoolManager schoolManager = schoolManagerManager.findSchoolManagerById(managerId);
        if (schoolManager == null) {
            return new Response<>(Response.FAIL, "学校管理员不存在", null);
        }
        return new Response<>(Response.SUCCESS, "获取成功", enterApplicationManager.findLastNDaysBySchoolManagerAndStatus(schoolManager, n));
    }


    public Response<EnterApplication> addEnterApplicationsByStudentId(RawEnterApplication enterApplication) {
        Student student = studentManager.findStudentById(enterApplication.getStudentId());
        if (student == null) {
            return new Response<>(Response.FAIL, "学生不存在", null);
        }
        if (Objects.equals(student.getStatus(), "inside")) {
            return new Response<>(Response.FAIL, "学生已在校", null);
        }
        enterApplicationManager.save(classAdapter.cookEnterApplication(enterApplication));
        return new Response<>(Response.SUCCESS, "添加成功", null);
    }
}
