package com.example.accesscontrolsystem.service.student;

import com.example.accesscontrolsystem.manager.DailyReportManager;
import com.example.accesscontrolsystem.model.entity.DailyReport;
import com.example.accesscontrolsystem.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("StudentDailyReportService")
public class DailyReportService {
    private final DailyReportManager dailyReportManager;
    @Autowired
    public DailyReportService(DailyReportManager dailyReportManager) {
        this.dailyReportManager = dailyReportManager;
    }
    public Response<List<DailyReport>> getDailyReports(Integer studentId) {
        if (studentId == null) {
            return new Response<>(Response.FAIL, "studentId为空", null);
        }
        return new Response<>(Response.SUCCESS, "成功", dailyReportManager.findAllByStudentId(studentId));
    }
}
