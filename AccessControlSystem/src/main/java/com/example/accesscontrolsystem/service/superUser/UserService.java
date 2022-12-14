package com.example.accesscontrolsystem.service.superUser;

import com.example.accesscontrolsystem.manager.CounsellorManager;
import com.example.accesscontrolsystem.manager.StudentManager;
import com.example.accesscontrolsystem.model.entity.user.Counsellor;
import com.example.accesscontrolsystem.model.entity.user.Student;
import com.example.accesscontrolsystem.manager.ClassManager;
import com.example.accesscontrolsystem.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("SuperUserUserService")
public class UserService {
    private final StudentManager studentManager;
    private final CounsellorManager counsellorManager;
    private final ClassManager classManager;

    @Autowired
    public UserService(StudentManager studentManager, CounsellorManager counsellorManager, ClassManager classManager) {
        this.studentManager = studentManager;
        this.counsellorManager = counsellorManager;
        this.classManager = classManager;
    }
    public Response<List<Student>> getStudents() {
        return new Response<>(Response.SUCCESS, "查询成功", studentManager.getStudents());
    }

    public Response<Student> addStudent(Student student) {
        if (studentManager.findStudentById(student.getId()) != null) {
            return new Response<>(Response.FAIL, "学生id已存在", null);
        }
        if (studentManager.findStudentByIdNumberAndIdType(student.getIdNumber(),student.getIdType()) != null) {
            return new Response<>(Response.FAIL, "学生身份证件号码已存在", null);
        }
        if (classManager.findClassById(student.getClassId()) == null) {
            return new Response<>(Response.FAIL, "学生班级不存在", null);
        }
        return new Response<>(Response.SUCCESS, "添加成功", studentManager.addStudent(student));
    }

    public Response<List<Counsellor>> getCounsellors() {
        return new Response<>(Response.SUCCESS, "查询成功", counsellorManager.getCounsellors());
    }
    public Response<Counsellor> addCounsellor(Counsellor counsellor) {
        if (counsellorManager.findCounsellorById(counsellor.getId()).isPresent()) {
            return new Response<>(Response.FAIL, "辅导员id已存在", null);
        }
        if (classManager.findClassById(counsellor.getClassId()) == null) {
            return new Response<>(Response.FAIL, "辅导员班级不存在", null);
        }
        return new Response<>(Response.SUCCESS, "添加成功", counsellorManager.addCounsellor(counsellor));
    }

    public Response<Student> getStudentById(Integer studentId) {
        if (studentManager.findStudentById(studentId) == null) {
            return new Response<>(Response.FAIL, "学生不存在", null);
        }
        return new Response<>(Response.SUCCESS, "成功", studentManager.findStudentById(studentId));
    }
}
