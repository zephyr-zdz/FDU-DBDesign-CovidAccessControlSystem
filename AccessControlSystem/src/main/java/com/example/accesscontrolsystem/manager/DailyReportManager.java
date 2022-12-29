package com.example.accesscontrolsystem.manager;

import com.example.accesscontrolsystem.mapper.DailyReportMapper;
import com.example.accesscontrolsystem.model.entity.reportNlog.DailyReport;
import com.example.accesscontrolsystem.service.system.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component("DailyReportManager")
public class DailyReportManager {
    private final DailyReportMapper dailyReportMapper;
    private final TimeService timeService;
    @Autowired
    public DailyReportManager(DailyReportMapper dailyReportMapper, TimeService timeService) {
        this.dailyReportMapper = dailyReportMapper;
        this.timeService = timeService;
    }

    public List<DailyReport> findAllByStudentId(Integer studentId) {
        return dailyReportMapper.findAllByStudentId(studentId);
    }

    public void addDailyReport(DailyReport dailyReport) {
        long today = timeService.getTime();
        dailyReport.setCreateTime(today);
        dailyReport.setDate(timeService.time2Day(today));
    }

    public DailyReport getDailyReportByStudentIdAndDate(Integer studentId, String date) {
        return dailyReportMapper.findDailyReportByStudentIdAndDate(studentId, date);
    }
}
