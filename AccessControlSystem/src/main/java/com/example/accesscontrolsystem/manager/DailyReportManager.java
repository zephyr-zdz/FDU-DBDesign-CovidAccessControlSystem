package com.example.accesscontrolsystem.manager;

import com.example.accesscontrolsystem.mapper.DailyReportMapper;
import com.example.accesscontrolsystem.model.entity.DailyReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("DailyReportManager")
public class DailyReportManager {
    private final DailyReportMapper dailyReportMapper;
    @Autowired
    public DailyReportManager(DailyReportMapper dailyReportMapper) {
        this.dailyReportMapper = dailyReportMapper;
    }

    public List<DailyReport> findAllByStudentId(Integer studentId) {
        return dailyReportMapper.findAllByStudentId(studentId);
    }
}
