package com.example.accesscontrolsystem.mapper;

import com.example.accesscontrolsystem.model.entity.reportNlog.GateLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GateLogMapper extends JpaRepository<GateLog, Integer> {
    @Query("select g from GateLog g where g.student.id = ?1 and g.time between ?2 and ?3")
    List<GateLog> findGateLogsByStudentIdAndTimeBetween(Integer studentId, Long nDaysBefore, Long today);

    @Query(value = "select * from gate_log where `student-id` = ?1 and direction = ?2 order by time DESC limit 1", nativeQuery = true)
    GateLog findGateLogByStudentIdAndDirectionIsOutOrderByTimeDesc(Integer studentId, String direction);

    @Query(value = """
            select `campus-id` from
            (select `campus-id`, count(*) as count\s
            from gate_log g where `student-major-id` = ?1 and g.time between ?2 and ?3\s
            GROUP BY `campus-id` ORDER BY count DESC\s
            LIMIT 1) as `c-ic`""", nativeQuery = true)
    Integer findMostLoggedCampusIdByStudent_Major_IdAndTimeBetween(Integer majorId, Long time1, Long time2);
}
