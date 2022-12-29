package com.example.accesscontrolsystem.manager;

import com.example.accesscontrolsystem.mapper.SchoolManagerMapper;
import com.example.accesscontrolsystem.model.entity.user.SchoolManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("SchoolManagerManager")
public class SchoolManagerManager {
    private final SchoolManagerMapper schoolManagerMapper;

    @Autowired
    public SchoolManagerManager(SchoolManagerMapper schoolManagerMapper) {
        this.schoolManagerMapper = schoolManagerMapper;

    }
    public SchoolManager findSchoolManagerById(Integer managerId) {
        return schoolManagerMapper.findSchoolManagerById(managerId);
    }
}
