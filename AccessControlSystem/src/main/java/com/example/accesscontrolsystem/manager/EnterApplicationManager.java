package com.example.accesscontrolsystem.manager;

import com.example.accesscontrolsystem.mapper.EnterApplicationMapper;
import com.example.accesscontrolsystem.model.entity.reportNlog.EnterApplication;
import com.example.accesscontrolsystem.model.entity.user.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component("EnterApplicationManager")
public class EnterApplicationManager {
    private final EnterApplicationMapper enterApplicationMapper;
    @Autowired
    public EnterApplicationManager(EnterApplicationMapper enterApplicationMapper) {
        this.enterApplicationMapper = enterApplicationMapper;
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
    public List<EnterApplication> findLastNDaysEnterApplications(Integer n) {
        Date today = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        List<String> nDays = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nDays.add(ft.format(new Date(today.getTime() - (long) i * 24 * 60 * 60 * 1000)));
        }
        List<EnterApplication> enterApplications = new ArrayList<>();
        nDays.stream().map(enterApplicationMapper::findAllByDate).forEach(enterApplications::addAll);
        return enterApplications;
    }
}
