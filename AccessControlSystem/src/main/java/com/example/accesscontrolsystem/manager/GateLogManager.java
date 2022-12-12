package com.example.accesscontrolsystem.manager;

import com.example.accesscontrolsystem.mapper.GateLogMapper;
import com.example.accesscontrolsystem.model.entity.reportNlog.GateLog;
import org.springframework.stereotype.Component;

@Component("GateLogManager")
public class GateLogManager {
    private final GateLogMapper gateLogMapper;

    public GateLogManager(GateLogMapper gateLogMapper) {
        this.gateLogMapper = gateLogMapper;
    }

    public void create(GateLog gateLog) {
        gateLogMapper.save(gateLog);
    }
}
