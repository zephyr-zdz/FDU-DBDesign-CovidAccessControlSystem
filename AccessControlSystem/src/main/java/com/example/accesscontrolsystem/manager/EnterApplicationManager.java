package com.example.accesscontrolsystem.manager;

import com.example.accesscontrolsystem.mapper.EnterApplicationMapper;
import com.example.accesscontrolsystem.model.entity.reportNlog.EnterApplication;
import com.example.accesscontrolsystem.model.entity.user.Counsellor;
import com.example.accesscontrolsystem.model.entity.user.SchoolManager;
import com.example.accesscontrolsystem.model.entity.user.Student;
import com.example.accesscontrolsystem.service.system.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("EnterApplicationManager")
public class EnterApplicationManager {
    private final EnterApplicationMapper enterApplicationMapper;
    private final TimeService timeService;
    @Autowired
    public EnterApplicationManager(EnterApplicationMapper enterApplicationMapper, TimeService timeService) {
        this.enterApplicationMapper = enterApplicationMapper;
        this.timeService = timeService;
    }
    public List<EnterApplication> findAllByStudentId(Integer studentId) {
        return enterApplicationMapper.findAllByStudentId(studentId);
    }
    public List<EnterApplication> findAllByStudentIdAndStatus(Integer studentId, String status) {
        return enterApplicationMapper.findAllByStudentIdAndStatus(studentId, status);
    }
    public List<EnterApplication> findAll() {
        return enterApplicationMapper.findAll();
    }
    public List<EnterApplication> findAllByStatus(String status) {
        return enterApplicationMapper.findAllByStatus(status);
    }
    public List<EnterApplication> findAllByStudents(List<Student> students) {
        List<EnterApplication> enterApplications = new ArrayList<>();
        students.stream().map(student -> findAllByStudentId(student.getId())).forEach(enterApplications::addAll);
        return enterApplications;
    }

    public void save(EnterApplication enterApplication) {
        enterApplicationMapper.save(enterApplication);
    }

    public List<EnterApplication> findLastNDaysByCounsellorAndStatus(Counsellor counsellor, Integer n) {
        long today = timeService.getTime();
        long nDaysBefore = timeService.getTimeNDaysBefore(n);
        return enterApplicationMapper.findAllByCounsellorAndStatusAndCreateTimeBetween(counsellor,"pending", today, nDaysBefore);
    }

    public List<EnterApplication> findLastNDaysBySchoolManagerAndStatus(SchoolManager schoolManager, Integer n) {
        long today = timeService.getTime();
        long nDaysBefore = timeService.getTimeNDaysBefore(n);
        return enterApplicationMapper.findAllByManagerAndStatusAndCreateTimeBetween(schoolManager,"counsellor", today, nDaysBefore);
    }

    public EnterApplication findEnterApplicationById(Integer applicationId) {
        return enterApplicationMapper.findEnterApplicationById(applicationId);
    }
}
