package com.example.accesscontrolsystem.mapper;

import com.example.accesscontrolsystem.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentMapper extends JpaRepository<Student, Integer> {
    Optional<Student> findStudentByName(String name);
    Optional<Student> findStudentById(Integer id);
    Optional<Student> findStudentByAuth(String auth);

    Optional<Student> findStudentByEmail(String email);
    Optional<Student> findStudentByIdNumberAndIdType(String idNumber, String idType);
}
