package com.example.accesscontrolsystem.controller.common;

import com.example.accesscontrolsystem.model.entity.reportNlog.DailyReport;
import com.example.accesscontrolsystem.service.DailyReportService;
import com.example.accesscontrolsystem.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController("CommonDailyReportController")
@RequestMapping("/daily-report")
public class DailyReportController {
    private final DailyReportService dailyReportService;
    @Autowired
    public DailyReportController(DailyReportService dailyReportService) {
        this.dailyReportService = dailyReportService;
    }

    @GetMapping("/")
    public Response<List<DailyReport>> getDailyReports(Integer studentId) {
        return dailyReportService.getDailyReports(studentId);
    }

    @GetMapping("/recent/")
    public Response<List<DailyReport>> getLastNDaysDailyReports(Integer classId,
                                                                Integer schoolId,
                                                                Integer studentId,
                                                                Integer n) {
        return dailyReportService.getLastNDaysDailyReports(classId, schoolId, studentId, n);
    }
}
