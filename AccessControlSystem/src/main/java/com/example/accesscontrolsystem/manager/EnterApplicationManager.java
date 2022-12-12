package com.example.accesscontrolsystem.manager;

import com.example.accesscontrolsystem.mapper.EnterApplicationMapper;
import com.example.accesscontrolsystem.model.entity.EnterApplication;
import com.example.accesscontrolsystem.model.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
}
