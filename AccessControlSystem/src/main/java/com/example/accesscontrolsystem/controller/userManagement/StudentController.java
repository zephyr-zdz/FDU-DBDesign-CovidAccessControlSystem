package com.example.accesscontrolsystem.controller.userManagement;

import com.example.accesscontrolsystem.model.entity.user.Student;
import com.example.accesscontrolsystem.model.vo.StudentWithLeaveTime;
import com.example.accesscontrolsystem.service.StudentDataService;
import com.example.accesscontrolsystem.service.UserService;
import com.example.accesscontrolsystem.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("SuperUserStudentController")
@RequestMapping("/student")
public class StudentController {
    private final UserService userservice;
    private final StudentDataService studentDataService;
    @Autowired
    public StudentController(UserService userservice, StudentDataService studentDataService) {
        this.userservice = userservice;
        this.studentDataService = studentDataService;
    }
    @GetMapping("/all")
    public Response<List<Student>> getStudents() {
        return userservice.getStudents();
    }

    @GetMapping("/enter-most-applying")
    public Response<List<Student>> getNStudentsWithMostEnterApplication(Integer classId,
                                                                        Integer schoolId,
                                                                        Integer n) {
        return studentDataService.getNStudentsWithMostLeaveTime(classId, schoolId, n);
    }
    @GetMapping("/avg-outside-longest")
    public Response<List<Student>> getNStudentsWithLongestAvgOutsideTime(Integer classId,
                                                                         Integer schoolId,
                                                                         Integer n) {
        return studentDataService.getNStudentsWithLongestAvgOutsideTime(classId, schoolId, n);
    }

    @GetMapping("/outside-duration/{studentId}")
    public Response<Double> getStudentOutsideDuration(@PathVariable Integer studentId) { // hours 一年内
        return studentDataService.getStudentOutsideDuration(studentId);
    }
    @GetMapping("/otaku/") // n天未离校
    public Response<List<Student>> getEnterApplicationsByStudentId(Integer classId,
                                                                   Integer schoolId,
                                                                   Integer n) {
        return studentDataService.getNDaysOtakus(classId, schoolId, n);
    }
    @GetMapping("/script-kiddie/") // n天填写日报分钟时间相同
    public Response<List<Student>> getNDaysScriptKiddies(Integer n) {
        return studentDataService.catchNDaysScriptKiddies(n);
    }
    @GetMapping("/applied-but-not-leaved/") // 申请离校但未离校
    public Response<List<Student>> getAppliedButNotLeaft(Integer classId,
                                                          Integer schoolId) {
        return studentDataService.getAppliedButNotLeaved(classId, schoolId);
    }
    @GetMapping("/leaved-but-not-applied/") // 离校但未申请
    public Response<List<Student>> getLeaved24hrsButNotApplied(Integer classId,
                                                               Integer schoolId) {
        return studentDataService.getLeaved24hrsButNotApplied(classId, schoolId);
    }
    @GetMapping("/outside/") //离校学生
    public Response<List<StudentWithLeaveTime>> getOutsideStudents(Integer classId,
                                                                   Integer schoolId) {
        return studentDataService.getOutsideStudents(classId, schoolId);
    }
}
