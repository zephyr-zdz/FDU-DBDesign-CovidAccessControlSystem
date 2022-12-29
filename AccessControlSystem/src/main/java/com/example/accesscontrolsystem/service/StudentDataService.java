package com.example.accesscontrolsystem.service;

import com.example.accesscontrolsystem.manager.GateLogManager;
import com.example.accesscontrolsystem.model.entity.reportNlog.GateLog;
import com.example.accesscontrolsystem.model.entity.user.Student;
import com.example.accesscontrolsystem.service.system.TimeService;
import com.example.accesscontrolsystem.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service("StudentDataService")

public class StudentDataService {
    private final GateLogManager gateLogManager;
    private final TimeService timeService;

    @Autowired
    public StudentDataService(GateLogManager gateLogManager, TimeService timeService) {
        this.gateLogManager = gateLogManager;
        this.timeService = timeService;
    }
    public Response<Double> getStudentOutsideDuration(Integer studentId) { // hours
        long duration = 0;
        List<GateLog> gateLogs = gateLogManager.getOneYearGateLogsByStudentId(studentId);
        long start = 0, end;
        for (GateLog gateLog : gateLogs) {
            if (gateLog.getDirection().equals("out")) {
                start = gateLog.getTime();
            }
            if (gateLog.getDirection().equals("in")) {
                end = gateLog.getTime();
                duration += end - start;
                start = 0;
            }
        }
        if (start != 0) {
            duration += timeService.getTime() - start;
        }
        return duration == 0 ? new Response<>(Response.SUCCESS,"成功",0.0) : new Response<>(Response.SUCCESS, "成功", new BigDecimal(duration / 3600000.0).setScale(2, RoundingMode.HALF_UP).doubleValue());
    }

    public Response<List<Student>> getNDaysOtakus(Integer classId, Integer schoolId, Integer n) {
        if (schoolId == -1) { // super admin
            return new Response<>(Response.SUCCESS, "获取成功", gateLogManager.findNDaysOtakus(n));
        } else if (classId == -1) { // school admin
            return new Response<>(Response.SUCCESS, "获取成功", gateLogManager.findNDaysOtakusBySchoolId(n, schoolId));
        } else if (classId > 0) { // counsellor
            return new Response<>(Response.SUCCESS, "获取成功", gateLogManager.findNDaysOtakusByMajorId(n, classId));
        } else {
            return new Response<>(Response.FAIL, "获取失败", null);
        }
    }
}
