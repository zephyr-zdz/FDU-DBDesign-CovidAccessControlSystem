package com.example.accesscontrolsystem.mapper;

import com.example.accesscontrolsystem.model.entity.DailyReport;
import com.example.accesscontrolsystem.model.entity.GateLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GateLogMapper extends JpaRepository<GateLog, Integer> {
    List<GateLog> findAllByStudentId(Integer studentId);
    GateLog findGateLogById(Integer id);
}
