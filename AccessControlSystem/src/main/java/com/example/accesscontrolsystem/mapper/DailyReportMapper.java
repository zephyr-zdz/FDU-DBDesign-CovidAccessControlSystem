package com.example.accesscontrolsystem.mapper;

import com.example.accesscontrolsystem.model.entity.reportNlog.DailyReport;
import com.example.accesscontrolsystem.model.entity.user.Student;
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

    @Query(value = """
            select * from student s
            where (select count(distinct(minute)) from daily_report
                   where `student-id` = s.id and `create-time` between ?1 and ?2)= 1""",
            nativeQuery = true)
    List<Student> findScriptKiddies(Long nDaysAgo, long today);

    @Query("""
            select d from DailyReport d
            where d.student.id = ?1 and d.student.major.id = ?2 and d.createTime between ?3 and ?4""")
    List<DailyReport> findAllByStudentIdAndStudentMajorIdAndCreateTimeBetween(Integer studentId, Integer schoolId, Long nDaysAgo, long today);

    @Query("""
            select d from DailyReport d
            where d.student.id = ?1 and d.student.myClass.id = ?2 and d.createTime between ?3 and ?4""")
    List<DailyReport> findAllByStudentIdAndStudentMyClassIdAndCreateTimeBetween(Integer studentId, Integer classId, Long nDaysAgo, long today);
}
