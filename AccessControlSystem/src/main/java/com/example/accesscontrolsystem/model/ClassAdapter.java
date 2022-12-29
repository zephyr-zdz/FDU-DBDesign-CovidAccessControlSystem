package com.example.accesscontrolsystem.model;

import com.example.accesscontrolsystem.manager.StudentManager;
import com.example.accesscontrolsystem.mapper.CampusMapper;
import com.example.accesscontrolsystem.mapper.GateLogMapper;
import com.example.accesscontrolsystem.model.entity.reportNlog.DailyReport;
import com.example.accesscontrolsystem.model.entity.reportNlog.EnterApplication;
import com.example.accesscontrolsystem.model.entity.reportNlog.GateLog;
import com.example.accesscontrolsystem.model.entity.reportNlog.LeaveApplication;
import com.example.accesscontrolsystem.model.entity.user.Student;
import com.example.accesscontrolsystem.model.vo.*;
import com.example.accesscontrolsystem.service.system.TimeService;
import org.springframework.stereotype.Component;

@Component("ClassAdapter")
public class ClassAdapter {
    private final TimeService timeService;
    private final StudentManager studentManager;
    private final CampusMapper campusMapper;
    private final GateLogMapper gateLogMapper;

    public ClassAdapter(TimeService timeService, StudentManager studentManager,
                        CampusMapper campusMapper,
                        GateLogMapper gateLogMapper) {
        this.timeService = timeService;
        this.studentManager = studentManager;
        this.campusMapper = campusMapper;
        this.gateLogMapper = gateLogMapper;
    }

    public EnterApplication cookEnterApplication(RawEnterApplication enterApplication) {
        EnterApplication application = new EnterApplication();
        application.setPassingAreas(enterApplication.getPassingAreas());
        application.setCreateTime(timeService.getTime());
        application.setEnterTime(enterApplication.getEnterTime()); // TODO: time format
        application.setStatus("pending");
        application.setStudent(studentManager.findStudentById(enterApplication.getStudentId()));
        application.setCounsellor(studentManager.findStudentById(enterApplication.getStudentId()).getMyClass().getCounsellor());
        application.setManager(studentManager.findStudentById(enterApplication.getStudentId()).getMyClass().getMajor().getSchoolManager());
        return application;
    }

    public LeaveApplication cookLeaveApplication(RawLeaveApplication leaveApplication) {
        LeaveApplication application = new LeaveApplication();
        application.setReason(leaveApplication.getReason());
        application.setDestination(leaveApplication.getDestination());
        application.setCreateTime(timeService.getTime());
        application.setLeaveTime(leaveApplication.getLeaveTime()); // TODO: time format
        application.setReturnTime(leaveApplication.getReturnTime()); // TODO: time format
        application.setStatus("pending");
        application.setStudent(studentManager.findStudentById(leaveApplication.getStudentId()));
        application.setCounsellor(studentManager.findStudentById(leaveApplication.getStudentId()).getMyClass().getCounsellor());
        application.setManager(studentManager.findStudentById(leaveApplication.getStudentId()).getMyClass().getMajor().getSchoolManager());
        return application;
    }

    public DailyReport cookDailyReport(RawDailyReport dailyReport) {
        DailyReport report = new DailyReport();
        report.setLocation(dailyReport.getLocation());
        report.setOther(dailyReport.getOther());
        report.setTemperature(dailyReport.getTemperature());
        report.setCreateTime(timeService.getTime());
        report.setMinute(timeService.time2Minute(report.getCreateTime()));
        report.setDate(timeService.time2Day(report.getCreateTime()));
        report.setStudent(studentManager.findStudentById(dailyReport.getStudentId()));
        return report;
    }

    public GateLog cookGateLog(RawGateLog gateLog) {
        GateLog log = new GateLog();
        log.setTime(timeService.getTime());
        log.setDirection(gateLog.getDirection());
        if (gateLog.getDirection().equals("in")) {
            double duration;
            GateLog lastOutLog = gateLogMapper.findGateLogByStudentIdAndDirectionIsOutOrderByTimeDesc(gateLog.getStudentId(), "out");
            duration = lastOutLog == null ? 0 : (double) (lastOutLog.getTime() - timeService.getTime()) / 1000 / 60;
            log.setLeaveDuration(duration);
        }
        log.setStudent(studentManager.findStudentById(gateLog.getStudentId()));
        log.setMajor(log.getStudent().getMajor());
        log.setCampus(campusMapper.findCampusById(gateLog.getCampusId()));
        return log;
    }
    public StudentWithLeaveTime cookStudentWithLeaveTime(Student student, long leaveTime) {
        StudentWithLeaveTime studentWithLeaveTime = new StudentWithLeaveTime();
        studentWithLeaveTime.setStudent(student);
        studentWithLeaveTime.setLeaveTimeStr(timeService.time2Str(leaveTime));
        return studentWithLeaveTime;
    }
}
