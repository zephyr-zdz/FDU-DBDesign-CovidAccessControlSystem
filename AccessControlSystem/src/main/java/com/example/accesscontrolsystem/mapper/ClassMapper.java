package com.example.accesscontrolsystem.mapper;

import com.example.accesscontrolsystem.model.entity.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassMapper extends JpaRepository<Class, Integer> {
    Class findClassById(Integer id);

    Class getClassById(Integer classId);

    List<Class> findClassesByMajorId(Integer majorId);
}
