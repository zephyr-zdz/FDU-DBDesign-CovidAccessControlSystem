package com.example.accesscontrolsystem.mapper;

import com.example.accesscontrolsystem.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper extends JpaRepository<Student, Integer> {
    Student findStudentByName(String name);
    Student findStudentById(Integer id);
    Student findStudentByAuth(String auth);

    Student findStudentByEmail(String email);
    Student findStudentByIdNumberAndIdType(String idNumber, String idType);

    List<Student> findStudentsByClassId(Integer id);
}
