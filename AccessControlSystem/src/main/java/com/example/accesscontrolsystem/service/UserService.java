package com.example.accesscontrolsystem.service;

import com.example.accesscontrolsystem.manager.CounsellorManager;
import com.example.accesscontrolsystem.manager.SchoolManagerManager;
import com.example.accesscontrolsystem.manager.StudentManager;
import com.example.accesscontrolsystem.model.entity.user.Counsellor;
import com.example.accesscontrolsystem.model.entity.user.SchoolManager;
import com.example.accesscontrolsystem.model.entity.user.Student;
import com.example.accesscontrolsystem.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("SuperUserUserService")
public class UserService {
    private final StudentManager studentManager;
    private final CounsellorManager counsellorManager;
    private final SchoolManagerManager schoolManagerManager;

    @Autowired
    public UserService(StudentManager studentManager, CounsellorManager counsellorManager, SchoolManagerManager schoolManagerManager) {
        this.studentManager = studentManager;
        this.counsellorManager = counsellorManager;
        this.schoolManagerManager = schoolManagerManager;
    }
    public Response<List<Student>> getStudents() {
        return new Response<>(Response.SUCCESS, "查询成功", studentManager.getStudents());
    }
    public Response<List<Counsellor>> getCounsellors() {
        return new Response<>(Response.SUCCESS, "查询成功", counsellorManager.getCounsellors());
    }
    public Response<Student> getStudentById(Integer studentId) {
        if (studentManager.findStudentById(studentId) == null) {
            return new Response<>(Response.FAIL, "学生不存在", null);
        }
        return new Response<>(Response.SUCCESS, "成功", studentManager.findStudentById(studentId));
    }

    public Response<Counsellor> getCounsellorById(Integer counsellorId) {
        if (counsellorManager.findCounsellorById(counsellorId) == null) {
            return new Response<>(Response.FAIL, "辅导员不存在", null);
        }
        return new Response<>(Response.SUCCESS, "成功", counsellorManager.findCounsellorById(counsellorId));
    }

    public Response<SchoolManager> getSchoolManagerById(Integer schoolManagerId) {
        if (counsellorManager.findCounsellorById(schoolManagerId) == null) {
            return new Response<>(Response.FAIL, "校长不存在", null);
        }
        return new Response<>(Response.SUCCESS, "成功", schoolManagerManager.findSchoolManagerById(schoolManagerId));
    }
}
