package com.example.accesscontrolsystem.manager;

import com.example.accesscontrolsystem.mapper.GateLogMapper;
import com.example.accesscontrolsystem.model.entity.reportNlog.GateLog;
import com.example.accesscontrolsystem.service.system.TimeService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("GateLogManager")
public class GateLogManager {
    private final GateLogMapper gateLogMapper;
    private final TimeService timeService;

    public GateLogManager(GateLogMapper gateLogMapper, TimeService timeService) {
        this.gateLogMapper = gateLogMapper;
        this.timeService = timeService;
    }

    public void create(GateLog gateLog) {
        gateLogMapper.save(gateLog);
    }

    public List<GateLog> getOneYearGateLogsByStudentId(Integer studentId) {
        Long today = timeService.getTime();
        Long nDaysBefore = timeService.getTimeNDaysBefore(365);
        return gateLogMapper.findGateLogsByStudentIdAndTimeBetween(studentId, nDaysBefore, today);
    }
}
