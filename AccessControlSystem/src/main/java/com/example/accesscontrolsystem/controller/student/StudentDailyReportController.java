package com.example.accesscontrolsystem.controller.student;

import com.example.accesscontrolsystem.model.entity.reportNlog.DailyReport;
import com.example.accesscontrolsystem.model.vo.RawDailyReport;
import com.example.accesscontrolsystem.service.DailyReportService;
import com.example.accesscontrolsystem.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("StudentDailyReportController")
@RequestMapping("/student/daily-report")
public class StudentDailyReportController {
    private final DailyReportService dailyReportService;
    @Autowired
    public StudentDailyReportController(DailyReportService dailyReportService) {
        this.dailyReportService = dailyReportService;
    }
    @PostMapping("/")
    public Response<DailyReport> addDailyReports(@RequestBody RawDailyReport dailyReport) {
        return dailyReportService.addDailyReport(dailyReport);
    }
}
