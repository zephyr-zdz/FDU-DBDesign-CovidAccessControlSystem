package com.example.accesscontrolsystem.mapper;

import com.example.accesscontrolsystem.model.entity.EnterApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnterApplicationMapper extends JpaRepository<EnterApplication, Integer> {
    List<EnterApplication> findAllByStudentId(Integer studentId);
    List<EnterApplication> findAllByStudentIdAndStatus(Integer studentId, String status);
}
