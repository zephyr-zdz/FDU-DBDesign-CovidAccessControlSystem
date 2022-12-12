package com.example.accesscontrolsystem.controller;

import com.example.accesscontrolsystem.model.entity.reportNlog.DailyReport;
import com.example.accesscontrolsystem.service.student.DailyReportService;
import com.example.accesscontrolsystem.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController("StudentDailyReportController")
@RequestMapping("/student/daily-report")
public class DailyReportController {
    private final DailyReportService dailyReportService;
    @Autowired
    public DailyReportController(DailyReportService dailyReportService) {
        this.dailyReportService = dailyReportService;
    }
    @PostMapping("/")
    public Response<DailyReport> addDailyReports(@RequestBody DailyReport dailyReport) {
        return dailyReportService.addDailyReport(dailyReport);
    }
    @GetMapping("/")
    public Response<List<DailyReport>> getDailyReports(@RequestParam("student-id") Integer studentId) {
        return dailyReportService.getDailyReports(studentId);
    }
}
