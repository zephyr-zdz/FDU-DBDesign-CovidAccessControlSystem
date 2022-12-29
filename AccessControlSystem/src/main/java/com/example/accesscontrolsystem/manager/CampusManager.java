package com.example.accesscontrolsystem.manager;

import com.example.accesscontrolsystem.mapper.CampusMapper;
import com.example.accesscontrolsystem.mapper.GateLogMapper;
import com.example.accesscontrolsystem.model.entity.Campus;
import com.example.accesscontrolsystem.service.system.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("CampusManager")
public class CampusManager
{
    private final CampusMapper campusMapper;
    private final GateLogMapper gateLogMapper;
    private final TimeService timeService;
    @Autowired
    public CampusManager(CampusMapper campusMapper, GateLogMapper gateLogMapper, TimeService timeService) {
        this.campusMapper = campusMapper;
        this.gateLogMapper = gateLogMapper;
        this.timeService = timeService;
    }
    public Campus findCampusById(Integer id) {
        return campusMapper.findCampusById(id);
    }

    public Campus majorNDaysMostLoggedCampus(Integer majorId, Integer n) {
        Long today = timeService.getTime();
        Long nDaysBefore = timeService.getTimeNDaysBefore(n);
        return campusMapper.findCampusById(gateLogMapper.findMostLoggedCampusIdByStudent_Major_IdAndTimeBetween(majorId, today, nDaysBefore));
    }
}
