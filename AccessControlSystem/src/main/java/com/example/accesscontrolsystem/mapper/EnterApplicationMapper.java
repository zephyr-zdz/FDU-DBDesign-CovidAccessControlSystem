package com.example.accesscontrolsystem.mapper;

import com.example.accesscontrolsystem.model.entity.reportNlog.EnterApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnterApplicationMapper extends JpaRepository<EnterApplication, Integer> {
    List<EnterApplication> findAllByStudentId(Integer studentId);
    List<EnterApplication> findAllByStudentIdAndStatus(Integer studentId, String status);
    List<EnterApplication> findAllByStatus(String status);
    @Query(value = "select * from enter_application where class_id = ?1 and school_id = ?2 and student_id = ?3 and status = ?4", nativeQuery = true)
    List<EnterApplication> sqlFind(String classId, String schoolId, String studentId, String status);

    List<EnterApplication> findAllByCreateTimeBetween(long now, long nDaysBefore);
}
