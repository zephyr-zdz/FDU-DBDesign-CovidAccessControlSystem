package com.example.accesscontrolsystem.mapper;

import com.example.accesscontrolsystem.model.entity.reportNlog.LeaveApplication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveApplicationMapper extends JpaRepository<LeaveApplication, Integer> {
    @Query("select l from LeaveApplication l where l.student.id = ?1")
    List<LeaveApplication> findAllByStudentId(Integer studentId);
    @Query("select l from LeaveApplication l where l.student.id = ?1 and l.status = ?2")
    List<LeaveApplication> findAllByStudentIdAndStatus(Integer studentId, String status);

    @Query("select l from LeaveApplication l where l.status = ?1")
    List<LeaveApplication> findAllByStatus(String status);
    @Query("""
            select l from LeaveApplication l
            where l.manager.id = ?1 and l.status = ?2 and l.createTime between ?3 and ?4""")
    List<LeaveApplication> findAllByManager_IdAndStatusAndCreateTimeBetween(Integer schoolManagerId, String pending, long nDaysBefore, long today);

    LeaveApplication findLeaveApplicationById(Integer applicationId);
    @Query("select l from LeaveApplication l where l.status = ?1 and l.createTime between ?2 and ?3")
    List<LeaveApplication> findAllByStatusAndCreateTimeBetween(String status, long nDaysBefore, long today);

    @Query("""
            select l from LeaveApplication l
            where l.counsellor.id = ?1 and l.status = ?2 and l.createTime between ?3 and ?4""")
    List<LeaveApplication> findAllByCounsellorIdAndStatusAndCreateTimeBetween(Integer counsellorId, String pending, long nDaysBefore, long today);
}
