package com.example.accesscontrolsystem.manager;

import com.example.accesscontrolsystem.mapper.MajorMapper;
import com.example.accesscontrolsystem.model.entity.Major;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("MajorManager")
public class MajorManager {
    private final MajorMapper majorMapper;
    @Autowired
    public MajorManager(MajorMapper majorMapper) {
        this.majorMapper = majorMapper;
    }
    public Major findMajorById(Integer id) {
        return majorMapper.findMajorById(id);
    }
}

