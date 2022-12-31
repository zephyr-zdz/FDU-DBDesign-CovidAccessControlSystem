package com.example.accesscontrolsystem.mapper;

import com.example.accesscontrolsystem.model.entity.reportNlog.LeaveApplication;
import com.example.accesscontrolsystem.model.entity.user.Counsellor;
import com.example.accesscontrolsystem.model.entity.user.SchoolManager;
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
            where l.counsellor = ?1 and l.status = ?2 and l.createTime between ?3 and ?4""")
    List<LeaveApplication> findAllByCounsellorAndStatusAndCreateTimeBetween(Counsellor counsellor, String status, long today, long nDaysBefore);

    @Query("select l from LeaveApplication l where l.manager = ?1 and l.status = ?2 and l.createTime between ?3 and ?4")
    List<LeaveApplication> findAllByManagerAndStatusAndCreateTimeBetween(SchoolManager schoolManager, String pending, long today, long nDaysBefore);

    LeaveApplication findLeaveApplicationById(Integer applicationId);
    @Query("select l from LeaveApplication l where l.status = ?1 and l.createTime between ?2 and ?3")
    List<LeaveApplication> findAllByStatusAndCreateTimeBetween(String status, long today, long nDaysBefore);
}
