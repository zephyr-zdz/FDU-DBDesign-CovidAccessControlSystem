package com.example.accesscontrolsystem.manager;

import com.example.accesscontrolsystem.mapper.GateLogMapper;
import com.example.accesscontrolsystem.mapper.StudentMapper;
import com.example.accesscontrolsystem.model.entity.reportNlog.GateLog;
import com.example.accesscontrolsystem.model.entity.user.Student;
import com.example.accesscontrolsystem.service.system.TimeService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("GateLogManager")
public class GateLogManager {
    private final GateLogMapper gateLogMapper;
    private final TimeService timeService;
    private final StudentMapper studentMapper;

    public GateLogManager(GateLogMapper gateLogMapper, TimeService timeService,
                          StudentMapper studentMapper) {
        this.gateLogMapper = gateLogMapper;
        this.timeService = timeService;
        this.studentMapper = studentMapper;
    }

    public void create(GateLog gateLog) {
        gateLogMapper.save(gateLog);
    }

    public List<GateLog> getOneYearGateLogsByStudentId(Integer studentId) {
        Long today = timeService.getTime();
        Long nDaysBefore = timeService.getTimeNDaysBefore(365);
        return gateLogMapper.findGateLogsByStudentIdAndTimeBetween(studentId, nDaysBefore, today);
    }

    public List<Student> findNDaysOtakus(Integer n) {
        Long today = timeService.getTime();
        Long nDaysBefore = timeService.getTimeNDaysBefore(n);
        return studentMapper.findOtakusByTimeBetween(nDaysBefore, today);
    }

    public List<Student> findNDaysOtakusBySchoolId(Integer n, Integer schoolId) {
        Long today = timeService.getTime();
        Long nDaysBefore = timeService.getTimeNDaysBefore(n);
        return studentMapper.findOtakusBySchoolIdAndTimeBetween(schoolId, nDaysBefore, today);
    }

    public List<Student> findNDaysOtakusByMajorId(Integer n, Integer classId) {
        Long today = timeService.getTime();
        Long nDaysBefore = timeService.getTimeNDaysBefore(n);
        return studentMapper.findOtakusByClassIdAndTimeBetween(classId, nDaysBefore, today);
    }
}
