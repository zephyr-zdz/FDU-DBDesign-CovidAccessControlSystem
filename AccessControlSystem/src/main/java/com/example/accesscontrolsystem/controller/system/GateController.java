package com.example.accesscontrolsystem.controller.system;

import com.example.accesscontrolsystem.model.entity.reportNlog.GateLog;
import com.example.accesscontrolsystem.service.system.GateService;
import com.example.accesscontrolsystem.util.Response;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController("SystemGateController")
@RequestMapping("/gate")
public class GateController {
    private final GateService gateService;
    @Autowired
    public GateController(GateService gateService) {
        this.gateService = gateService;
    }
    @PostMapping("/gate")
    public Response<String> gate(@RequestBody GateLog gateLog){
        return gateService.gate(gateLog);
    }
}
