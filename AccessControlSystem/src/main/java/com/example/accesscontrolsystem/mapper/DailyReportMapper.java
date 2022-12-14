package com.example.accesscontrolsystem.mapper;

import com.example.accesscontrolsystem.model.entity.reportNlog.DailyReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DailyReportMapper extends JpaRepository<DailyReport, Integer> {
    List<DailyReport> findAllByStudentId(Integer studentId);

    DailyReport findDailyReportByStudentIdAndDate(Integer studentId, String date);
}
