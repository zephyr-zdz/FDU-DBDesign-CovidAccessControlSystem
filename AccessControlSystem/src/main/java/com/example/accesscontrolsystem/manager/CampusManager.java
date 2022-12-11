package com.example.accesscontrolsystem.manager;

import com.example.accesscontrolsystem.mapper.CampusMapper;
import com.example.accesscontrolsystem.model.entity.Campus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("CampusManager")
public class CampusManager
{
    private final CampusMapper campusMapper;
    @Autowired
    public CampusManager(CampusMapper campusMapper) {
        this.campusMapper = campusMapper;
    }
    public Campus findCampusById(Integer id) {
        return campusMapper.findCampusById(id);
    }
}
