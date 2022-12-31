package com.example.accesscontrolsystem.mapper;

import com.example.accesscontrolsystem.model.entity.reportNlog.EnterApplication;
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

    EnterApplication findEnterApplicationById(Integer applicationId);

    List<EnterApplication> findAllByStatusAndCreateTimeBetween(String pending, long today, long nDaysBefore);

    List<EnterApplication> findAllByCounsellorIdAndStatusAndCreateTimeBetween(Integer counsellorId, String pending, long today, long nDaysBefore);

    List<EnterApplication> findAllByManagerIdAndStatusAndCreateTimeBetween(Integer schoolManagerId, String counsellor, long today, long nDaysBefore);
}
