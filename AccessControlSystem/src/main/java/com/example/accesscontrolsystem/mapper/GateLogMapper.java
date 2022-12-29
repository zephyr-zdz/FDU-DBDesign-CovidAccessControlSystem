package com.example.accesscontrolsystem.mapper;

import com.example.accesscontrolsystem.model.entity.reportNlog.GateLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GateLogMapper extends JpaRepository<GateLog, Integer> {
    List<GateLog> findAllByStudentId(Integer studentId);
    GateLog findGateLogById(Integer id);

    @Query("select g from GateLog g where g.student.id = ?1 and g.time between ?2 and ?3")
    List<GateLog> findGateLogsByStudentIdAndTimeBetween(Integer studentId, Long nDaysBefore, Long today);
}
