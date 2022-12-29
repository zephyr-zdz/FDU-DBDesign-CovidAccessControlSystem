package com.example.accesscontrolsystem.model;

import com.example.accesscontrolsystem.manager.StudentManager;
import com.example.accesscontrolsystem.mapper.CampusMapper;
import com.example.accesscontrolsystem.model.entity.reportNlog.DailyReport;
import com.example.accesscontrolsystem.model.entity.reportNlog.EnterApplication;
import com.example.accesscontrolsystem.model.entity.reportNlog.GateLog;
import com.example.accesscontrolsystem.model.entity.reportNlog.LeaveApplication;
import com.example.accesscontrolsystem.model.vo.RawDailyReport;
import com.example.accesscontrolsystem.model.vo.RawEnterApplication;
import com.example.accesscontrolsystem.model.vo.RawGateLog;
import com.example.accesscontrolsystem.model.vo.RawLeaveApplication;
import com.example.accesscontrolsystem.service.system.TimeService;
import org.springframework.stereotype.Component;

@Component("ClassAdapter")
public class ClassAdapter {
    private final TimeService timeService;
    private final StudentManager studentManager;
    private final CampusMapper campusMapper;

    public ClassAdapter(TimeService timeService, StudentManager studentManager,
                        CampusMapper campusMapper) {
        this.timeService = timeService;
        this.studentManager = studentManager;
        this.campusMapper = campusMapper;
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
        report.setDate(timeService.getDate());
        report.setStudent(studentManager.findStudentById(dailyReport.getStudentId()));
        return report;
    }

    public GateLog cookGateLog(RawGateLog gateLog) {
        GateLog log = new GateLog();
        log.setTime(timeService.getTime());
        log.setDirection(gateLog.getDirection());
        log.setStudent(studentManager.findStudentById(gateLog.getStudentId()));
        log.setCampus(campusMapper.findCampusById(gateLog.getCampusId()));
        return log;
    }
}
