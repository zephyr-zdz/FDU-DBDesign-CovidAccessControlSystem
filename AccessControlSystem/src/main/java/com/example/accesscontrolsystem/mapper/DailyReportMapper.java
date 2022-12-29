package com.example.accesscontrolsystem.mapper;

import com.example.accesscontrolsystem.model.entity.reportNlog.DailyReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DailyReportMapper extends JpaRepository<DailyReport, Integer> {
    @Query("select d from DailyReport d where d.student.id = ?1")
    List<DailyReport> findAllByStudentId(Integer studentId);

    @Query("select d from DailyReport d where d.student.id = ?1 and d.date = ?2")
    DailyReport findDailyReportByStudentIdAndDate(Integer studentId, String date);

    @Query("select d from DailyReport d where d.student.id = ?1 and d.createTime between ?2 and ?3")
    List<DailyReport> findAllByStudentIdAndCreateTimeBetween(Integer studentId, Long nDaysAgo, long today);
}
