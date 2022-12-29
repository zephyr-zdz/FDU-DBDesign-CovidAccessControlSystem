package com.example.accesscontrolsystem.mapper;

import com.example.accesscontrolsystem.model.entity.reportNlog.EnterApplication;
import com.example.accesscontrolsystem.model.entity.user.Counsellor;
import com.example.accesscontrolsystem.model.entity.user.SchoolManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnterApplicationMapper extends JpaRepository<EnterApplication, Integer> {
    @Query("select e from EnterApplication e where e.student.id = ?1")
    List<EnterApplication> findAllByStudentId(Integer studentId);
    @Query("select e from EnterApplication e where e.student.id = ?1 and e.status = ?2")
    List<EnterApplication> findAllByStudentIdAndStatus(Integer studentId, String status);
    @Query("select e from EnterApplication e where e.status = ?1")
    List<EnterApplication> findAllByStatus(String status);

    @Query("""
            select e from EnterApplication e
            where e.counsellor = ?1 and e.status = ?2 and e.createTime between ?3 and ?4
            """)
    List<EnterApplication> findAllByCounsellorAndStatusAndCreateTimeBetween(Counsellor counsellor, String status, long today, long nDaysBefore);

    @Query("select e from EnterApplication e where e.manager = ?1 and e.status = ?2 and e.createTime between ?3 and ?4")
    List<EnterApplication> findAllByManagerAndStatusAndCreateTimeBetween(SchoolManager manager, String counsellor, long today, long nDaysBefore);
}
