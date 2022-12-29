package com.example.accesscontrolsystem.mapper;

import com.example.accesscontrolsystem.model.entity.user.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper extends JpaRepository<Student, Integer> {
    @Query("select s from Student s where s.id = ?1")
    Student findStudentById(Integer id);
    @Query("select s from Student s where s.email = ?1")
    Student findStudentByEmail(String email);
    @Query("select s from Student s where s.idNumber = ?1 and s.idType = ?2")
    Student findStudentByIdNumberAndIdType(String idNumber, String idType);

    @Query("select s from Student s where s.myClass.id = ?1")
    List<Student> findStudentsByMyClass_Id(Integer classId);

    @Query(value = """
        select *
        from student s
        where `status` = 'in'
        and not EXISTS
            (select *\s
            from gate_log g
            where g.direction = 'in' and g.time between ?1 and ?2 and g.`student-id` = s.id)""", nativeQuery = true)
    List<Student> findOtakusByTimeBetween(Long nDaysBefore, Long today);

    @Query(value = """
        select *
        from student s
        where `status` = 'in' and `major-id` = ?1
        and not EXISTS
            (select *\s
            from gate_log g
            where g.direction = 'in' and g.time between ?2 and ?3 and g.`student-id` = s.id)""", nativeQuery = true)
    List<Student> findOtakusBySchoolIdAndTimeBetween(Integer schoolId, Long nDaysBefore, Long today);

    @Query(value = """
        select *
        from student s
        where `status` = 'in' and `class-id` = ?1
        and not EXISTS
            (select *\s
            from gate_log g
            where g.direction = 'in' and g.time between ?2 and ?3 and g.`student-id` = s.id)""", nativeQuery = true)
    List<Student> findOtakusByClassIdAndTimeBetween(Integer classId, Long nDaysBefore, Long today);
}
