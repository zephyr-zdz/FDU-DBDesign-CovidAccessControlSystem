package com.example.accesscontrolsystem.manager;

import com.example.accesscontrolsystem.mapper.EnterApplicationMapper;
import com.example.accesscontrolsystem.model.entity.EnterApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
}
