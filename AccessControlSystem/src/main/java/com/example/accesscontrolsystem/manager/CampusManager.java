package com.example.accesscontrolsystem.manager;

import com.example.accesscontrolsystem.mapper.CampusMapper;
import com.example.accesscontrolsystem.mapper.GateLogMapper;
import com.example.accesscontrolsystem.mapper.MajorMapper;
import com.example.accesscontrolsystem.model.entity.Campus;
import com.example.accesscontrolsystem.model.entity.Major;
import com.example.accesscontrolsystem.model.vo.MajorWithCampus;
import com.example.accesscontrolsystem.service.system.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("CampusManager")
public class CampusManager
{
    private final CampusMapper campusMapper;
    private final GateLogMapper gateLogMapper;
    private final MajorMapper majorMapper;
    private final TimeService timeService;
    @Autowired
    public CampusManager(CampusMapper campusMapper, GateLogMapper gateLogMapper, MajorMapper majorMapper, TimeService timeService) {
        this.campusMapper = campusMapper;
        this.gateLogMapper = gateLogMapper;
        this.majorMapper = majorMapper;
        this.timeService = timeService;
    }
    public Campus findCampusById(Integer id) {
        return campusMapper.findCampusById(id);
    }

    public List<MajorWithCampus> allMajorWithMostLoggedCampusNDays(Integer n) {
        Long time1 = timeService.getTimeNDaysBefore(n);
        Long time2 = timeService.getTime();
        List<Major> majors = majorMapper.findAll();
        return majors.stream().map(major -> {
            MajorWithCampus majorWithCampus = new MajorWithCampus();
            majorWithCampus.setMajor(major);
            Campus campus = campusMapper.findCampusById(gateLogMapper.findMostLoggedCampusIdByStudent_Major_IdAndTimeBetween(major.getId(), time1, time2));
            majorWithCampus.setCampus(campus);
            return majorWithCampus;
        }).toList();
    }
}
