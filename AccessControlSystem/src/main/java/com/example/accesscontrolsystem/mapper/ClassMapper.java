package com.example.accesscontrolsystem.mapper;

import com.example.accesscontrolsystem.model.entity.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClassMapper extends JpaRepository<Class, Integer> {
    Optional<Class> findClassById(Integer id);
}
